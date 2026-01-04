package net.foxtrot.ca3e.drill;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.*;

public class DrillRegistry {

    public record Drop(ItemStack result, float chance) {}
    public record DrillRecipe(Block block, int processingTime, List<Drop> drops, boolean requiresSuperpower) {}

    private static final Map<Block, DrillRecipe> MAP = new HashMap<>();

    public static void add(Block block, int processingTime, List<Drop> drops, boolean requiresSuperpower) {
        MAP.put(block, new DrillRecipe(block, processingTime, List.copyOf(drops), requiresSuperpower));
    }

    public static DrillRecipe get(Block block) {
        return MAP.get(block);
    }
}
