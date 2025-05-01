package com.neofast.arbor_mechanica;

import com.neofast.arbor_mechanica.block.Blocks2;
import com.neofast.arbor_mechanica.block.entities.TileEntities;
import com.neofast.arbor_mechanica.item.Items;
import com.neofast.arbor_mechanica.network.Menus;
import com.neofast.arbor_mechanica.network.custom.CuttingMachineScreen;
import com.neofast.arbor_mechanica.network.custom.NT_Machine1Screen;
import com.neofast.arbor_mechanica.network.custom.NT_Machine2Screen;
import com.neofast.arbor_mechanica.recipes.Recipes;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ArborMechanica.MOD_ID)
public class ArborMechanica {
    public static final String MOD_ID = "arbor_mechanica";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ArborMechanica(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        Items.register(modEventBus);
        Blocks2.register(modEventBus);
        TileEntities.register(modEventBus);
        Menus.register(modEventBus);
        Recipes.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(Items.WOODEN_GEAR);
            event.accept(Items.SAPLING_GEAR);
            event.accept(Items.TWINE_CORD);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(Blocks2.WOODEN_GEAR_BOX);
            event.accept(Blocks2.NT_MACHINE1);
            event.accept(Blocks2.NT_MACHINE2);
            event.accept(Blocks2.CUTTINGMACHINE);
            event.accept(Blocks2.NATURA_COLLECTOR);
            event.accept(Blocks2.MOSSY_CORE);
            event.accept(Blocks2.ROOTED_CONDUIT);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(Menus.NT_MACHINE1_MENU.get(), NT_Machine1Screen::new);
            event.register(Menus.NT_MACHINE2_MENU.get(), NT_Machine2Screen::new);
            event.register(Menus.CUTTINGMACHINE_MENU.get(), CuttingMachineScreen::new);
        }
    }
}
