package net.foxtrot.ca3e.blockentity;

import net.foxtrot.ca3e.block.ModBlockEntities;
import net.foxtrot.ca3e.client.sound.DoohickeyLoopSound;
import net.foxtrot.ca3e.drill.DrillRegistry;
import net.foxtrot.ca3e.menu.MiningDoohickeyMenu;
import net.foxtrot.ca3e.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MiningDoohickeyBlockEntity extends BlockEntity implements MenuProvider, GeoBlockEntity {

    public static final int SLOT_FUEL = 0;
    public static final int SLOT_DRILL_DISPLAY = 1;
    public static final int SLOT_OUT_START = 2;
    public static final int SLOT_OUT_END = 9;

    public enum MachineState {
        IDLE,
        DRILLING,
        SUPERCHARGED,
        STOPPING
    }

    private static final RawAnimation ANIM_IDLE = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation ANIM_DRILL_START = RawAnimation.begin().thenPlay("drill_start");
    private static final RawAnimation ANIM_DRILL_STOP = RawAnimation.begin().thenPlay("drill_stop");
    private static final RawAnimation ANIM_DRILLING = RawAnimation.begin().thenLoop("drilling");

    private static final int START_ANIM_TICKS = 40;
    private static final int STOP_ANIM_TICKS = 30;
    private static final float SUPERCHARGED_SPEED_MULTIPLIER = 2.0f;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final ItemStackHandler items = new ItemStackHandler(10) {
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == SLOT_FUEL) return isFuel(stack);
            return false;
        }
    };

    private final LazyOptional<IItemHandler> itemCap = LazyOptional.of(() -> items);

    public int burnTime = 0;
    public int burnTimeTotal = 0;
    public int progress = 0;
    public int progressMax = 100;
    public int superpowerTicks = 0;

    public boolean running = false;

    private MachineState machineState = MachineState.IDLE;
    private int startAnimTicks = 0;
    private int stopAnimTicks = 40;

    private ItemStack lastDisplay = ItemStack.EMPTY;

    @OnlyIn(Dist.CLIENT)
    private transient DoohickeyLoopSound drillLoop;

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> burnTime;
                case 1 -> burnTimeTotal;
                case 2 -> progress;
                case 3 -> progressMax;
                case 4 -> superpowerTicks;
                case 5 -> machineState.ordinal();
                case 6 -> startAnimTicks;
                case 7 -> stopAnimTicks;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> burnTime = value;
                case 1 -> burnTimeTotal = value;
                case 2 -> progress = value;
                case 3 -> progressMax = value;
                case 4 -> superpowerTicks = value;
                case 5 -> {
                    if (value >= 0 && value < MachineState.values().length) machineState = MachineState.values()[value];
                }
                case 6 -> startAnimTicks = value;
                case 7 -> stopAnimTicks = value;
            }
        }

        @Override
        public int getCount() {
            return 8;
        }
    };

    public MiningDoohickeyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINING_DOOHICKEY.get(), pos, state);
    }

    public MachineState getMachineState() {
        return machineState;
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<MiningDoohickeyBlockEntity> state) {
        state.getController().setAnimationSpeed(this.machineState == MachineState.SUPERCHARGED ? SUPERCHARGED_SPEED_MULTIPLIER : 1.0);

        if (this.machineState == MachineState.STOPPING) return state.setAndContinue(ANIM_DRILL_STOP);
        if (this.machineState == MachineState.IDLE) return state.setAndContinue(ANIM_IDLE);
        if (this.startAnimTicks > 0) return state.setAndContinue(ANIM_DRILL_START);
        return state.setAndContinue(ANIM_DRILLING);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public ItemStackHandler getItems() {
        return this.items;
    }

    public ContainerData getData() {
        return this.data;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Mining Doohickey");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MiningDoohickeyMenu(id, inventory, this, this.data);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) return itemCap.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemCap.invalidate();
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) handleUpdateTag(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("Items", items.serializeNBT());
        tag.putInt("BurnTime", burnTime);
        tag.putInt("BurnTimeTotal", burnTimeTotal);
        tag.putInt("Progress", progress);
        tag.putInt("ProgressMax", progressMax);
        tag.putInt("SuperpowerTicks", superpowerTicks);
        tag.putInt("MachineState", machineState.ordinal());
        tag.putInt("StartAnimTicks", startAnimTicks);
        tag.putInt("StopAnimTicks", stopAnimTicks);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        items.deserializeNBT(tag.getCompound("Items"));
        burnTime = tag.getInt("BurnTime");
        burnTimeTotal = tag.getInt("BurnTimeTotal");
        progress = tag.getInt("Progress");
        progressMax = tag.getInt("ProgressMax");
        superpowerTicks = tag.getInt("SuperpowerTicks");

        int ms = tag.getInt("MachineState");
        if (ms >= 0 && ms < MachineState.values().length) machineState = MachineState.values()[ms];
        else machineState = MachineState.IDLE;

        startAnimTicks = tag.getInt("StartAnimTicks");
        stopAnimTicks = tag.getInt("StopAnimTicks");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MiningDoohickeyBlockEntity be) {
        if (level.isClientSide) return;

        Block belowBlock = level.getBlockState(pos.below()).getBlock();
        DrillRegistry.DrillRecipe recipe = DrillRegistry.get(belowBlock);

        ItemStack display = ItemStack.EMPTY;
        if (recipe != null) {
            Item item = belowBlock.asItem();
            if (item != Items.AIR) display = new ItemStack(item);
        }

        if (!ItemStack.matches(display, be.lastDisplay)) {
            be.lastDisplay = display.copy();
            be.items.setStackInSlot(SLOT_DRILL_DISPLAY, display.copy());
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }

        if (be.superpowerTicks > 0) be.superpowerTicks--;

        boolean canWork = recipe != null;
        if (canWork) {
            be.progressMax = recipe.processingTime();
        } else {
            be.progress = 0;
            be.progressMax = 100;
        }

        if (be.burnTime > 0) be.burnTime--;

        if (be.burnTime == 0 && canWork) {
            ItemStack fuel = be.items.getStackInSlot(SLOT_FUEL);
            if (!fuel.isEmpty() && be.isFuel(fuel)) {
                int burn = be.getFuelBurnTime(fuel);
                boolean superFuel = be.isSuperFuel(fuel);
                fuel.shrink(1);
                be.burnTime = burn;
                be.burnTimeTotal = burn;
                if (superFuel) be.superpowerTicks = Math.max(be.superpowerTicks, 2400);
                be.setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }

        boolean powered = be.burnTime > 0;
        boolean superReqOk = recipe == null || !recipe.requiresSuperpower() || be.superpowerTicks > 0;

        be.running = powered && canWork && superReqOk;

        MachineState prev = be.machineState;
        MachineState desired = MachineState.IDLE;
        if (be.running) desired = be.superpowerTicks > 0 ? MachineState.SUPERCHARGED : MachineState.DRILLING;

        boolean stateChanged = false;

        if (prev != desired) {
            if (desired == MachineState.IDLE) {
                if (prev != MachineState.STOPPING) {
                    be.machineState = MachineState.STOPPING;
                    be.stopAnimTicks = STOP_ANIM_TICKS;
                    be.startAnimTicks = 0;
                    stateChanged = true;
                }
            } else {
                be.machineState = desired;
                if (prev == MachineState.IDLE || prev == MachineState.STOPPING) {
                    be.stopAnimTicks = 0;
                    int startDuration = desired == MachineState.SUPERCHARGED
                            ? Math.max(1, START_ANIM_TICKS / 2)
                            : START_ANIM_TICKS;
                    be.startAnimTicks = startDuration;
                    float pitch = desired == MachineState.SUPERCHARGED ? SUPERCHARGED_SPEED_MULTIPLIER : 1.0f;
                    level.playSound(null, pos, ModSounds.DRILL_SPINUP.get(), net.minecraft.sounds.SoundSource.BLOCKS, 0.25f, pitch);
                } else {
                    be.startAnimTicks = 0;
                }
                stateChanged = true;
            }
        }

        if (stateChanged) {
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }

        if (be.startAnimTicks > 0) {
            be.startAnimTicks = Math.max(0, be.startAnimTicks - 1);
        }
        if (be.stopAnimTicks > 0) {
            be.stopAnimTicks--;
            if (be.stopAnimTicks == 0 && be.machineState == MachineState.STOPPING) {
                be.machineState = MachineState.IDLE;
                be.setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }

        if (!be.running) {
            be.progress = 0;
            return;
        }

        if (be.machineState == MachineState.SUPERCHARGED && level instanceof ServerLevel sl) {
            if ((level.getGameTime() & 1L) == 0L) {
                double x = pos.getX() + 0.5;
                double y = pos.getY() + 1.05;
                double z = pos.getZ() + 0.5;
                sl.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 6, 0.18, 0.12, 0.18, 0.02);
            }
        }

        int step = be.machineState == MachineState.SUPERCHARGED ? 2 : 1;
        be.progress += step;

        while (be.progress >= be.progressMax) {
            be.progress -= be.progressMax;
            be.rollOutputs(recipe);
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
            if (!be.running) break;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientTick(Level level, BlockPos pos, BlockState state, MiningDoohickeyBlockEntity be) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.level != level) return;

        boolean drillingState = be.getMachineState() == MachineState.DRILLING
                || be.getMachineState() == MachineState.SUPERCHARGED;
        boolean shouldLoop = drillingState && be.startAnimTicks == 0;
        if (!shouldLoop) {
            if (be.drillLoop != null) {
                be.drillLoop.end();
                be.drillLoop = null;
            }
            return;
        }

        float pitch = be.getMachineState() == MachineState.SUPERCHARGED ? SUPERCHARGED_SPEED_MULTIPLIER : 1.0f;

        if (be.drillLoop == null) {
            be.drillLoop = new DoohickeyLoopSound(be, ModSounds.DRILL_LOOP.get(), net.minecraft.sounds.SoundSource.BLOCKS, 1.0f, pitch);
            mc.getSoundManager().play(be.drillLoop);
        } else {
            be.drillLoop.setPitch(pitch);
            if (be.drillLoop.isEnding()) be.drillLoop = null;
        }
    }

    private void rollOutputs(DrillRegistry.DrillRecipe recipe) {
        RandomSource rng = level != null ? level.random : RandomSource.create();
        for (DrillRegistry.Drop d : recipe.drops()) {
            if (rng.nextFloat() <= d.chance()) {
                insertIntoOutputs(d.result().copy());
            }
        }
    }

    private void insertIntoOutputs(ItemStack stack) {
        for (int i = SLOT_OUT_START; i <= SLOT_OUT_END; i++) {
            if (stack.isEmpty()) return;
            ItemStack existing = items.getStackInSlot(i);
            if (existing.isEmpty()) {
                items.setStackInSlot(i, stack);
                return;
            }
            if (ItemStack.isSameItemSameTags(existing, stack)) {
                int max = Math.min(existing.getMaxStackSize(), 64);
                int space = max - existing.getCount();
                if (space > 0) {
                    int move = Math.min(space, stack.getCount());
                    existing.grow(move);
                    stack.shrink(move);
                    items.setStackInSlot(i, existing);
                }
            }
        }
    }

    private boolean isFuel(ItemStack stack) {
        return stack.is(Items.COAL) || stack.is(Items.CHARCOAL) || isBlazeCake(stack);
    }

    private boolean isSuperFuel(ItemStack stack) {
        return isBlazeCake(stack);
    }

    private boolean isBlazeCake(ItemStack stack) {
        Item createCake = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("create", "blaze_cake"));
        Item createAdditionCake = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("createaddition", "blaze_cake"));
        return (createCake != null && stack.is(createCake)) || (createAdditionCake != null && stack.is(createAdditionCake));
    }

    private int getFuelBurnTime(ItemStack stack) {
        if (stack.is(Items.COAL) || stack.is(Items.CHARCOAL)) return 1600;
        if (isBlazeCake(stack)) return 3200;
        return 0;
    }
}
