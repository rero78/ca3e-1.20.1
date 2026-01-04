package net.foxtrot.ca3e;

import com.mojang.logging.LogUtils;
import net.foxtrot.ca3e.block.ModBlockEntities;
import net.foxtrot.ca3e.block.ModBlocks;
import net.foxtrot.ca3e.drill.DrillRegistryInit;
import net.foxtrot.ca3e.entity.ModEntities;
import net.foxtrot.ca3e.entity.client.MushletRenderer;
import net.foxtrot.ca3e.item.ModItems;
import net.foxtrot.ca3e.menu.ModMenus;
import net.foxtrot.ca3e.sound.ModSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CataclysmAwaits.MOD_ID)
public class CataclysmAwaits
{
    public static final String MOD_ID = "ca3e";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CataclysmAwaits(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenus.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(DrillRegistryInit::init);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {

            // Mechanisms
            event.accept(ModItems.ANDESITE_MECHANISM);
            event.accept(ModItems.INCOMPLETE_ANDESITE_MECHANISM);
            event.accept(ModItems.PREPARED_ANDESITE_MECHANISM);

            event.accept(ModItems.PNEUMATIC_MECHANISM);
            event.accept(ModItems.INCOMPLETE_PNEUMATIC_MECHANISM);

            event.accept(ModItems.STERILE_MECHANISM);
            event.accept(ModItems.INCOMPLETE_STERILE_MECHANISM);

            event.accept(ModItems.INDUSTRIAL_MECHANISM);
            event.accept(ModItems.INCOMPLETE_INDUSTRIAL_MECHANISM);

            event.accept(ModItems.COMPUTATION_MECHANISM);
            event.accept(ModItems.INCOMPLETE_COMPUTATION_MECHANISM);

            event.accept(ModItems.ADVANCED_COMPUTATION_MECHANISM);
            event.accept(ModItems.INCOMPLETE_ADVANCED_COMPUTATION_MECHANISM);

            event.accept(ModItems.ULTRA_LIGHTWEIGHT_MECHANISM);
            event.accept(ModItems.INCOMPLETE_ULTRA_LIGHTWEIGHT_MECHANISM);

            event.accept(ModItems.HEAVY_DUTY_MECHANISM);
            event.accept(ModItems.INCOMPLETE_HEAVY_DUTY_MECHANISM);

            // Sheets
            event.accept(ModItems.ANDESITE_SHEET);
            event.accept(ModItems.SILVER_SHEET);
            event.accept(ModItems.INVAR_SHEET);
            event.accept(ModItems.SIGNALUM_SHEET);
            event.accept(ModItems.NETHERITE_SHEET);

            // ALUMINUM

            event.accept(ModItems.ALUMINUM_INGOT);
            event.accept(ModItems.ALUMINUM_SHEET);
            event.accept(ModItems.RAW_BAUXITE);
            event.accept(ModBlocks.BAUXITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_BAUXITE_ORE);

            event.accept(ModBlocks.MINING_DOOHICKEY);

        }
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.MUSHLET.get(), MushletRenderer::new);
        }
    }
}
