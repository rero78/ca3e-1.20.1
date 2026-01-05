package net.foxtrot.ca3e.sound;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CataclysmAwaits.MOD_ID);

    public static final RegistryObject<SoundEvent> DRILL_SPINUP =
            SOUNDS.register("mining_doohickey.drill_spinup",
                    () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "mining_doohickey.drill_spinup")));

    public static final RegistryObject<SoundEvent> DRILL_LOOP =
            SOUNDS.register("mining_doohickey.drill_loop",
                    () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "mining_doohickey.drill_loop")));

    public static final RegistryObject<SoundEvent> SUPER_LOOP =
            SOUNDS.register("mining_doohickey.super_loop",
                    () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "mining_doohickey.super_loop")));

    public static void register(IEventBus bus) {
        SOUNDS.register(bus);
    }
}
