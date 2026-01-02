package net.foxtrot.ca3e.event;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.entity.ModEntities;
import net.foxtrot.ca3e.entity.custom.MushletEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CataclysmAwaits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MUSHLET.get(), MushletEntity.createAttributes().build());
    }
}