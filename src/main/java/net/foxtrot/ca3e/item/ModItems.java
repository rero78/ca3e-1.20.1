package net.foxtrot.ca3e.item;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CataclysmAwaits.MOD_ID);

    public static final RegistryObject<Item> ANDESITE_MECHANISM = ITEMS.register("andesite_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_ANDESITE_MECHANISM = ITEMS.register("incomplete_andesite_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANDESITE_SHEET = ITEMS.register("andesite_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PREPARED_ANDESITE_MECHANISM = ITEMS.register("prepared_andesite_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PNEUMATIC_MECHANISM = ITEMS.register("pneumatic_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_PNEUMATIC_MECHANISM = ITEMS.register("incomplete_pneumatic_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STERILE_MECHANISM = ITEMS.register("sterile_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_STERILE_MECHANISM = ITEMS.register("incomplete_sterile_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SHEET = ITEMS.register("silver_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INDUSTRIAL_MECHANISM = ITEMS.register("industrial_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_INDUSTRIAL_MECHANISM = ITEMS.register("incomplete_industrial_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COMPUTATION_MECHANISM = ITEMS.register("computation_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_COMPUTATION_MECHANISM = ITEMS.register("incomplete_computation_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INVAR_SHEET = ITEMS.register("invar_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVANCED_COMPUTATION_MECHANISM = ITEMS.register("advanced_computation_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_ADVANCED_COMPUTATION_MECHANISM = ITEMS.register("incomplete_advanced_computation_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIGNALUM_SHEET = ITEMS.register("signalum_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ULTRA_LIGHTWEIGHT_MECHANISM = ITEMS.register("ultra_lightweight_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_ULTRA_LIGHTWEIGHT_MECHANISM = ITEMS.register("incomplete_ultra_lightweight_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_SHEET = ITEMS.register("aluminum_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BAUXITE = ITEMS.register("raw_bauxite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEAVY_DUTY_MECHANISM = ITEMS.register("heavy_duty_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INCOMPLETE_HEAVY_DUTY_MECHANISM = ITEMS.register("incomplete_heavy_duty_mechanism", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SHEET = ITEMS.register("netherite_sheet", () -> new Item(new Item.Properties()));
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
