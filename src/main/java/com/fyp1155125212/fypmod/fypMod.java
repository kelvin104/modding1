package com.fyp1155125212.fypmod;

import com.fyp1155125212.fypmod.entity.custom.NeutralCitizen;
import com.fyp1155125212.fypmod.entity.renderer.NeutralCitizenRenderer;
import com.fyp1155125212.fypmod.entity.renderer.NeutralCitizen_JRenderer;
import com.fyp1155125212.fypmod.entity.renderer.NeutralCitizen_NRenderer;
import com.fyp1155125212.fypmod.entity.renderer.PoliceRenderer;
import com.fyp1155125212.fypmod.init.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(fypMod.MOD_ID)
public class fypMod
{
    public static final String MOD_ID = "fypmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public fypMod() {
        // Register the setup method for modloading

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        EffectInit.EFFECTS.register(eventBus);
        EffectInit.POTIONS.register(eventBus);
        StructureInit.register(eventBus);
        EntityTypesInit.ENTITY_TYPES.register(eventBus);
        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        EffectInit.addPotionRecipes();
        event.enqueueWork( () -> {
            StructureInit.setupStructures();
                }

        );
        DeferredWorkQueue.runLater(
                () -> {
                    GlobalEntityTypeAttributes.put(EntityTypesInit.NEUTRAL_CITIZEN.get(), NeutralCitizen.setAttributes().create());
                    GlobalEntityTypeAttributes.put(EntityTypesInit.NEUTRAL_CITIZEN_J.get(), NeutralCitizen.setAttributes().create());
                    GlobalEntityTypeAttributes.put(EntityTypesInit.NEUTRAL_CITIZEN_N.get(), NeutralCitizen.setAttributes().create());
                    GlobalEntityTypeAttributes.put(EntityTypesInit.POLICE.get(), NeutralCitizen.setAttributes().create());
                }
        );

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.POLICE.get(), PoliceRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.NEUTRAL_CITIZEN.get(), NeutralCitizenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.NEUTRAL_CITIZEN_J.get(), NeutralCitizen_JRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.NEUTRAL_CITIZEN_N.get(), NeutralCitizen_NRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
