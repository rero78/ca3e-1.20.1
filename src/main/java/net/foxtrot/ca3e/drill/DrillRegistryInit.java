package net.foxtrot.ca3e.drill;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class DrillRegistryInit {

    public static void init() {
        DrillRegistry.add(Blocks.STONE, 80, List.of(
                new DrillRegistry.Drop(new ItemStack(Items.FLINT), 0.15f),
                new DrillRegistry.Drop(new ItemStack(Items.COAL), 0.10f)
        ), false);

        DrillRegistry.add(Blocks.DIORITE, 100, List.of(
                new DrillRegistry.Drop(new ItemStack(Items.QUARTZ), 0.20f)
        ), false);

        DrillRegistry.add(Blocks.ANDESITE, 120, List.of(
                new DrillRegistry.Drop(new ItemStack(Items.IRON_NUGGET), 0.35f)
        ), false);

        DrillRegistry.add(Blocks.DEEPSLATE, 140, List.of(
                new DrillRegistry.Drop(new ItemStack(Items.DIAMOND), 0.02f)
        ), true);
    }
}
