package net.foxtrot.ca3e.event;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.entity.client.ModModelLayers;
import net.foxtrot.ca3e.entity.client.MushletModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CataclysmAwaits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MUSHLET_LAYER, MushletModel::createBodyLayer);
    }
}