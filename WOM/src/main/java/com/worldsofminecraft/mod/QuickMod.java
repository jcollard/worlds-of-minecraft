package com.worldsofminecraft.mod;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.worldsofminecraft.mod.item.ItemExtender;
import com.worldsofminecraft.mod.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file

public abstract class QuickMod {
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();
	public final MinecraftMod.Builder BUILDER;

	public QuickMod() {
		if (FMLJavaModLoadingContext.get() != null) {
			Utils.getInstance().setLive(true);
		}
		BUILDER = getBuilder();
		init();
	}

	private void init() {
		if (FMLJavaModLoadingContext.get() == null) {
			return;
		}
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		// Register the setup method for modloading
		bus.addListener(this::setup);
		// Register the enqueueIMC method for modloading
		bus.addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		bus.addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		bus.addListener(this::doClientStuff);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		BUILDER.registerItems(bus);

	}

	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}

	private static Map<Item, Map<ResourceLocation, IItemPropertyGetter>> ITEM_PROPERTIES = null;

	private void doClientStuff(final FMLClientSetupEvent event) {

		initItemProperties();
		BUILDER.getItems().forEach((key, item) -> {
			if (item instanceof ItemExtender) {
				ItemExtender ie = (ItemExtender) item;
				Item modeled = ie.getVanillaItem().SUPPLIER.get();
				Utils.getInstance().getLogger().info("Copying properties from \"" + modeled.getRegistryName() + "\" to \"" + BUILDER.MOD_ID + ":" + ie.getSimpleRegistryName() + "\".");
				if(!ITEM_PROPERTIES.containsKey(modeled)) {
					Utils.getInstance().getLogger().info("No properties found.");
					return;
				}
				
				Map<ResourceLocation, IItemPropertyGetter> itemProperties = ITEM_PROPERTIES.get(modeled);
				for(Entry<ResourceLocation, IItemPropertyGetter> e : itemProperties.entrySet()) {
					Utils.getInstance().getLogger().info("Copying " + e.getKey());
					RegistryObject<Item> registryObject = ie.getRegistryObject();
					Item instance = registryObject.get();
					ItemModelsProperties.register(instance, e.getKey(), e.getValue());
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public static Map<Item, Map<ResourceLocation, IItemPropertyGetter>> initItemProperties() {
		if (ITEM_PROPERTIES == null) {
			try {
				Field f = ItemModelsProperties.class.getDeclaredField("PROPERTIES");
				f.setAccessible(true);
				Utils.getInstance().getLogger().info("Attempting to extract properties.");
				ITEM_PROPERTIES = (Map<Item, Map<ResourceLocation, IItemPropertyGetter>>) f.get(null);
				Utils.getInstance().getLogger().info(ITEM_PROPERTIES);
				Utils.getInstance().getLogger().info(ITEM_PROPERTIES.keySet());
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				
				e.printStackTrace();
				throw new IllegalStateException("Could not initialze ITEM_PROPERTIES.");
			}
		}
		return ITEM_PROPERTIES;
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("examplemod", "helloworld", () -> {
			LOGGER.info("Hello world from the MDK");
			return "Hello world";
		});
	}

	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}",
				event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically subscribe events on the
	// contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}
	}

	public abstract MinecraftMod.Builder getBuilder();
}
