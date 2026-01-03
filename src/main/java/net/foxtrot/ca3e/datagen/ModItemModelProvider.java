package net.foxtrot.ca3e.datagen;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CataclysmAwaits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ANDESITE_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_ANDESITE_MECHANISM);
        simpleItem(ModItems.ANDESITE_SHEET);
        simpleItem(ModItems.PREPARED_ANDESITE_MECHANISM);
        simpleItem(ModItems.PNEUMATIC_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_PNEUMATIC_MECHANISM);
        simpleItem(ModItems.STERILE_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_STERILE_MECHANISM);
        simpleItem(ModItems.SILVER_SHEET);
        simpleItem(ModItems.INDUSTRIAL_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_INDUSTRIAL_MECHANISM);
        simpleItem(ModItems.COMPUTATION_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_COMPUTATION_MECHANISM);
        simpleItem(ModItems.INVAR_SHEET);
        simpleItem(ModItems.ADVANCED_COMPUTATION_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_ADVANCED_COMPUTATION_MECHANISM);
        simpleItem(ModItems.SIGNALUM_SHEET);
        simpleItem(ModItems.ULTRA_LIGHTWEIGHT_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_ULTRA_LIGHTWEIGHT_MECHANISM);
        simpleItem(ModItems.HEAVY_DUTY_MECHANISM);
        simpleItem(ModItems.INCOMPLETE_HEAVY_DUTY_MECHANISM);
        simpleItem(ModItems.NETHERITE_SHEET);
        simpleItem(ModItems.RAW_BAUXITE);
        simpleItem(ModItems.ALUMINUM_SHEET);
        simpleItem(ModItems.ALUMINUM_INGOT);
        blockItem(ModItems.MINING_DOOHICKEY, "block/mining_doohickey");
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/generated")).texture("layer0",
                ResourceLocation.tryBuild(CataclysmAwaits.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder blockItem(RegistryObject<Item> item, String blockTexturePath) {
        return withExistingParent(item.getId().getPath(), mcLoc("block/cube_all"))
                .texture("all", modLoc(blockTexturePath));
    }
}
