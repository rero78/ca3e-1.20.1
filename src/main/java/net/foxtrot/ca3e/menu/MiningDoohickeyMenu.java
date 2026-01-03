package net.foxtrot.ca3e.menu;

import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.foxtrot.ca3e.menu.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class MiningDoohickeyMenu extends AbstractContainerMenu {

    private final MiningDoohickeyBlockEntity be;
    private final ContainerData data;

    public MiningDoohickeyMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, (MiningDoohickeyBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos()), null);
    }

    public MiningDoohickeyMenu(int id, Inventory inv, MiningDoohickeyBlockEntity be, ContainerData data) {
        super(ModMenus.MINING_DOOHICKEY.get(), id);
        this.be = be;
        this.data = data != null ? data : be.getData();

        addSlot(new SlotItemHandler(be.getItems(), 1, 18, 54));
        addSlot(new SlotItemHandler(be.getItems(), 0, 56, 54));

        int index = 2;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                addSlot(new SlotItemHandler(be.getItems(), index++, 110 + col * 18, 18 + row * 18));
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
        if (slot != null && slot.hasItem()) {
            ItemStack in = slot.getItem();
            stack = in.copy();

            if (index < 2) {
                if (!moveItemStackTo(in, 2, slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if (!moveItemStackTo(in, 0, 1, false)) {
                    if (!moveItemStackTo(in, 1, 2, false)) return ItemStack.EMPTY;
                }
            }

            if (in.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }
}
