package net.foxtrot.ca3e.entity;

import com.mojang.datafixers.kinds.IdF;
import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.entity.custom.MushletEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CataclysmAwaits.MOD_ID);

    public static final RegistryObject<EntityType<MushletEntity>> MUSHLET =
            ENTITY_TYPES.register("mushlet", () -> EntityType.Builder.of(MushletEntity::new, MobCategory.CREATURE)
                    .sized(0.3f, 0.5f).build("mushlet"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}