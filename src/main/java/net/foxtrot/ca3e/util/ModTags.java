package net.foxtrot.ca3e.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> FORGE_PLATES_ALUMINUM = tag("forge", "plates/aluminum");
        public static final TagKey<Item> TCONSTRUCT_INGOTS_ALUMINUM = tag("tconstruct", "ingots/aluminum");
        public static final TagKey<Item> TCONSTRUCT_PLATES_ALUMINUM = tag("tconstruct", "plates/aluminum");
        public static final TagKey<Item> TCONSTRUCT_RAW_MATERIALS_ALUMINUM = tag("tconstruct", "raw_materials/aluminum");
        public static final TagKey<Item> TCONSTRUCT_ORES_ALUMINUM = tag("tconstruct", "ores/aluminum");

        private static TagKey<Item> tag(String namespace, String path) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> FORGE_ORES_ALUMINUM = tag("forge", "ores/aluminum");
        public static final TagKey<Block> TCONSTRUCT_ORES_ALUMINUM = tag("tconstruct", "ores/aluminum");

        private static TagKey<Block> tag(String namespace, String path) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, path));
        }
    }
}
