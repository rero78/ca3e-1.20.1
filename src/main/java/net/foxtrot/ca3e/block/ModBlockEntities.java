package net.foxtrot.ca3e.block;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.block.ModBlocks;
import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CataclysmAwaits.MOD_ID);

    public static final RegistryObject<BlockEntityType<MiningDoohickeyBlockEntity>> MINING_DOOHICKEY =
            BLOCK_ENTITIES.register("mining_doohickey",
                    () -> BlockEntityType.Builder.of(
                            MiningDoohickeyBlockEntity::new,
                            ModBlocks.MINING_DOOHICKEY.get()
                    ).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
