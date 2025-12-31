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

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
