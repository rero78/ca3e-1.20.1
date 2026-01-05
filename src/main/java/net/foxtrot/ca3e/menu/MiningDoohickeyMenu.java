package net.foxtrot.ca3e.menu;

import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.foxtrot.ca3e.menu.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class MiningDoohickeyMenu extends AbstractContainerMenu {

    private final MiningDoohickeyBlockEntity be;
    private final ContainerData data;

    private static Item createBlazeCake() {
        return ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("create", "blaze_cake"));
    }

    private static Item createAdditionBlazeCake() {
        return ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("createaddition", "blaze_cake"));
    }

    private static boolean isAllowedFuel(ItemStack stack) {
        if (stack.is(Items.COAL) || stack.is(Items.CHARCOAL)) return true;
        Item a = createBlazeCake();
        Item b = createAdditionBlazeCake();
        return (a != null && stack.is(a)) || (b != null && stack.is(b));
    }

    private static class DisplaySlot extends SlotItemHandler {
        public DisplaySlot(IItemHandler handler, int index, int x, int y) { super(handler, index, x, y); }
        @Override public boolean mayPlace(ItemStack stack) { return false; }
        @Override public boolean mayPickup(Player player) { return false; }
    }

    private static class FuelSlot extends SlotItemHandler {
        public FuelSlot(IItemHandler handler, int index, int x, int y) { super(handler, index, x, y); }
        @Override public boolean mayPlace(ItemStack stack) { return isAllowedFuel(stack); }
    }

    private static class OutputSlot extends SlotItemHandler {
        public OutputSlot(IItemHandler handler, int index, int x, int y) { super(handler, index, x, y); }
        @Override public boolean mayPlace(ItemStack stack) { return false; }
    }

    public MiningDoohickeyMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, (MiningDoohickeyBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos()), null);
    }

    public MiningDoohickeyMenu(int id, Inventory inv, MiningDoohickeyBlockEntity be, ContainerData data) {
        super(ModMenus.MINING_DOOHICKEY.get(), id);
        this.be = be;
        this.data = data != null ? data : be.getData();

        addSlot(new DisplaySlot(be.getItems(), 1, 18, 54));
        addSlot(new FuelSlot(be.getItems(), 0, 56, 54));

        int idx = 2;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (idx > 9) break;
                addSlot(new OutputSlot(be.getItems(), idx++, 110 + col * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, 9 + row * 9 + col, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }

        addDataSlots(this.data);
    }

    public MiningDoohickeyBlockEntity getBlockEntity() {
        return be;
    }

    public ContainerData getData() {
        return data;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack in = slot.getItem();
        stack = in.copy();

        int machineSlots = 10;
        int playerInvStart = machineSlots;
        int playerInvEnd = slots.size();

        if (index < machineSlots) {
            if (!moveItemStackTo(in, playerInvStart, playerInvEnd, true)) return ItemStack.EMPTY;
        } else {
            if (isAllowedFuel(in)) {
                if (!moveItemStackTo(in, 1, 2, false)) return ItemStack.EMPTY;
            } else {
                return ItemStack.EMPTY;
            }
        }

        if (in.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();

        return stack;
    }
}
