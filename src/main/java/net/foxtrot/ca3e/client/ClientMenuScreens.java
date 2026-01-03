package net.foxtrot.ca3e.client;

import net.foxtrot.ca3e.client.screen.MiningDoohickeyScreen;
import net.foxtrot.ca3e.menu.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.foxtrot.ca3e.CataclysmAwaits;

@Mod.EventBusSubscriber(modid = CataclysmAwaits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientMenuScreens {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenus.MINING_DOOHICKEY.get(), MiningDoohickeyScreen::new));
    }
}
