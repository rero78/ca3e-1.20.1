package net.foxtrot.ca3e.datagen;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.block.ModBlocks;
import net.foxtrot.ca3e.item.ModItems;
import net.foxtrot.ca3e.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, CataclysmAwaits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(Tags.Items.INGOTS_ALUMINUM).add(ModItems.ALUMINUM_INGOT.get());
        this.tag(ModTags.Items.TCONSTRUCT_INGOTS_ALUMINUM).add(ModItems.ALUMINUM_INGOT.get());

        this.tag(Tags.Items.RAW_MATERIALS_ALUMINUM).add(ModItems.RAW_BAUXITE.get());
        this.tag(ModTags.Items.TCONSTRUCT_RAW_MATERIALS_ALUMINUM).add(ModItems.RAW_BAUXITE.get());

        this.tag(ModTags.Items.FORGE_PLATES_ALUMINUM).add(ModItems.ALUMINUM_SHEET.get());
        this.tag(ModTags.Items.TCONSTRUCT_PLATES_ALUMINUM).add(ModItems.ALUMINUM_SHEET.get());

        this.tag(Tags.Items.ORES_ALUMINUM)
                .add(ModBlocks.BAUXITE_ORE.get().asItem(),
                        ModBlocks.DEEPSLATE_BAUXITE_ORE.get().asItem());
        this.tag(ModTags.Items.TCONSTRUCT_ORES_ALUMINUM)
                .add(ModBlocks.BAUXITE_ORE.get().asItem(),
                        ModBlocks.DEEPSLATE_BAUXITE_ORE.get().asItem());
    }
}
