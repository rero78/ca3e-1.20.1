package net.foxtrot.ca3e.menu;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.menu.MiningDoohickeyMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CataclysmAwaits.MOD_ID);

    public static final RegistryObject<MenuType<MiningDoohickeyMenu>> MINING_DOOHICKEY =
            MENUS.register("mining_doohickey", () -> IForgeMenuType.create(MiningDoohickeyMenu::new));

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
