package net.foxtrot.ca3e.client;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.client.render.MiningDoohickeyRenderer;
import net.foxtrot.ca3e.block.ModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CataclysmAwaits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MINING_DOOHICKEY.get(), MiningDoohickeyRenderer::new);
    }
}
