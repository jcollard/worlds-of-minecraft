package com.worldsofminecraft.resource.vanilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import com.worldsofminecraft.mod.util.Volatile;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

/**
 * A {@link VanillaItem} is a reference to an item that comes as part of Vanilla
 * Minecraft.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
public enum VanillaItem {

    ACACIA_BOAT("acacia_boat", () -> Items.ACACIA_BOAT), ACACIA_BUTTON("acacia_button", () -> Items.ACACIA_BUTTON),
    ACACIA_DOOR("acacia_door", () -> Items.ACACIA_DOOR), ACACIA_FENCE("acacia_fence", () -> Items.ACACIA_FENCE),
    ACACIA_FENCE_GATE("acacia_fence_gate", () -> Items.ACACIA_FENCE_GATE),
    ACACIA_LEAVES("acacia_leaves", () -> Items.ACACIA_LEAVES), ACACIA_LOG("acacia_log", () -> Items.ACACIA_LOG),
    ACACIA_PLANKS("acacia_planks", () -> Items.ACACIA_PLANKS),
    ACACIA_PRESSURE_PLATE("acacia_pressure_plate", () -> Items.ACACIA_PRESSURE_PLATE),
    ACACIA_SAPLING("acacia_sapling", () -> Items.ACACIA_SAPLING), ACACIA_SIGN("acacia_sign", () -> Items.ACACIA_SIGN),
    ACACIA_SLAB("acacia_slab", () -> Items.ACACIA_SLAB), ACACIA_STAIRS("acacia_stairs", () -> Items.ACACIA_STAIRS),
    ACACIA_TRAPDOOR("acacia_trapdoor", () -> Items.ACACIA_TRAPDOOR),
    ACACIA_WOOD("acacia_wood", () -> Items.ACACIA_WOOD), ACTIVATOR_RAIL("activator_rail", () -> Items.ACTIVATOR_RAIL),
    AIR("air", () -> Items.AIR), ALLIUM("allium", () -> Items.ALLIUM),
    ANCIENT_DEBRIS("ancient_debris", () -> Items.ANCIENT_DEBRIS), ANDESITE("andesite", () -> Items.ANDESITE),
    ANDESITE_SLAB("andesite_slab", () -> Items.ANDESITE_SLAB),
    ANDESITE_STAIRS("andesite_stairs", () -> Items.ANDESITE_STAIRS),
    ANDESITE_WALL("andesite_wall", () -> Items.ANDESITE_WALL), ANVIL("anvil", () -> Items.ANVIL),
    APPLE("apple", () -> Items.APPLE), ARMOR_STAND("armor_stand", () -> Items.ARMOR_STAND),
    ARROW("arrow", () -> Items.ARROW), AZURE_BLUET("azure_bluet", () -> Items.AZURE_BLUET),
    BAKED_POTATO("baked_potato", () -> Items.BAKED_POTATO), BAMBOO("bamboo", () -> Items.BAMBOO),
    BARREL("barrel", () -> Items.BARREL), BARRIER("barrier", () -> Items.BARRIER), BASALT("basalt", () -> Items.BASALT),
    BAT_SPAWN_EGG("bat_spawn_egg", () -> Items.BAT_SPAWN_EGG), BEACON("beacon", () -> Items.BEACON),
    BEDROCK("bedrock", () -> Items.BEDROCK), BEE_NEST("bee_nest", () -> Items.BEE_NEST),
    BEE_SPAWN_EGG("bee_spawn_egg", () -> Items.BEE_SPAWN_EGG), BEEF("beef", () -> Items.BEEF),
    BEEHIVE("beehive", () -> Items.BEEHIVE), BEETROOT("beetroot", () -> Items.BEETROOT),
    BEETROOT_SEEDS("beetroot_seeds", () -> Items.BEETROOT_SEEDS),
    BEETROOT_SOUP("beetroot_soup", () -> Items.BEETROOT_SOUP), BELL("bell", () -> Items.BELL),
    BIRCH_BOAT("birch_boat", () -> Items.BIRCH_BOAT), BIRCH_BUTTON("birch_button", () -> Items.BIRCH_BUTTON),
    BIRCH_DOOR("birch_door", () -> Items.BIRCH_DOOR), BIRCH_FENCE("birch_fence", () -> Items.BIRCH_FENCE),
    BIRCH_FENCE_GATE("birch_fence_gate", () -> Items.BIRCH_FENCE_GATE),
    BIRCH_LEAVES("birch_leaves", () -> Items.BIRCH_LEAVES), BIRCH_LOG("birch_log", () -> Items.BIRCH_LOG),
    BIRCH_PLANKS("birch_planks", () -> Items.BIRCH_PLANKS),
    BIRCH_PRESSURE_PLATE("birch_pressure_plate", () -> Items.BIRCH_PRESSURE_PLATE),
    BIRCH_SAPLING("birch_sapling", () -> Items.BIRCH_SAPLING), BIRCH_SIGN("birch_sign", () -> Items.BIRCH_SIGN),
    BIRCH_SLAB("birch_slab", () -> Items.BIRCH_SLAB), BIRCH_STAIRS("birch_stairs", () -> Items.BIRCH_STAIRS),
    BIRCH_TRAPDOOR("birch_trapdoor", () -> Items.BIRCH_TRAPDOOR), BIRCH_WOOD("birch_wood", () -> Items.BIRCH_WOOD),
    BLACK_BANNER("black_banner", () -> Items.BLACK_BANNER), BLACK_BED("black_bed", () -> Items.BLACK_BED),
    BLACK_CARPET("black_carpet", () -> Items.BLACK_CARPET),
    BLACK_CONCRETE("black_concrete", () -> Items.BLACK_CONCRETE),
    BLACK_CONCRETE_POWDER("black_concrete_powder", () -> Items.BLACK_CONCRETE_POWDER),
    BLACK_DYE("black_dye", () -> Items.BLACK_DYE),
    BLACK_GLAZED_TERRACOTTA("black_glazed_terracotta", () -> Items.BLACK_GLAZED_TERRACOTTA),
    BLACK_SHULKER_BOX("black_shulker_box", () -> Items.BLACK_SHULKER_BOX),
    BLACK_STAINED_GLASS("black_stained_glass", () -> Items.BLACK_STAINED_GLASS),
    BLACK_STAINED_GLASS_PANE("black_stained_glass_pane", () -> Items.BLACK_STAINED_GLASS_PANE),
    BLACK_TERRACOTTA("black_terracotta", () -> Items.BLACK_TERRACOTTA),
    BLACK_WOOL("black_wool", () -> Items.BLACK_WOOL), BLACKSTONE("blackstone", () -> Items.BLACKSTONE),
    BLACKSTONE_SLAB("blackstone_slab", () -> Items.BLACKSTONE_SLAB),
    BLACKSTONE_STAIRS("blackstone_stairs", () -> Items.BLACKSTONE_STAIRS),
    BLACKSTONE_WALL("blackstone_wall", () -> Items.BLACKSTONE_WALL),
    BLAST_FURNACE("blast_furnace", () -> Items.BLAST_FURNACE), BLAZE_POWDER("blaze_powder", () -> Items.BLAZE_POWDER),
    BLAZE_ROD("blaze_rod", () -> Items.BLAZE_ROD), BLAZE_SPAWN_EGG("blaze_spawn_egg", () -> Items.BLAZE_SPAWN_EGG),
    BLUE_BANNER("blue_banner", () -> Items.BLUE_BANNER), BLUE_BED("blue_bed", () -> Items.BLUE_BED),
    BLUE_CARPET("blue_carpet", () -> Items.BLUE_CARPET), BLUE_CONCRETE("blue_concrete", () -> Items.BLUE_CONCRETE),
    BLUE_CONCRETE_POWDER("blue_concrete_powder", () -> Items.BLUE_CONCRETE_POWDER),
    BLUE_DYE("blue_dye", () -> Items.BLUE_DYE),
    BLUE_GLAZED_TERRACOTTA("blue_glazed_terracotta", () -> Items.BLUE_GLAZED_TERRACOTTA),
    BLUE_ICE("blue_ice", () -> Items.BLUE_ICE), BLUE_ORCHID("blue_orchid", () -> Items.BLUE_ORCHID),
    BLUE_SHULKER_BOX("blue_shulker_box", () -> Items.BLUE_SHULKER_BOX),
    BLUE_STAINED_GLASS("blue_stained_glass", () -> Items.BLUE_STAINED_GLASS),
    BLUE_STAINED_GLASS_PANE("blue_stained_glass_pane", () -> Items.BLUE_STAINED_GLASS_PANE),
    BLUE_TERRACOTTA("blue_terracotta", () -> Items.BLUE_TERRACOTTA), BLUE_WOOL("blue_wool", () -> Items.BLUE_WOOL),
    BONE("bone", () -> Items.BONE), BONE_BLOCK("bone_block", () -> Items.BONE_BLOCK),
    BONE_MEAL("bone_meal", () -> Items.BONE_MEAL), BOOK("book", () -> Items.BOOK),
    BOOKSHELF("bookshelf", () -> Items.BOOKSHELF), BOW("bow", () -> Items.BOW), BOWL("bowl", () -> Items.BOWL),
    BRAIN_CORAL("brain_coral", () -> Items.BRAIN_CORAL),
    BRAIN_CORAL_BLOCK("brain_coral_block", () -> Items.BRAIN_CORAL_BLOCK),
    BRAIN_CORAL_FAN("brain_coral_fan", () -> Items.BRAIN_CORAL_FAN), BREAD("bread", () -> Items.BREAD),
    BREWING_STAND("brewing_stand", () -> Items.BREWING_STAND), BRICK("brick", () -> Items.BRICK),
    BRICK_SLAB("brick_slab", () -> Items.BRICK_SLAB), BRICK_STAIRS("brick_stairs", () -> Items.BRICK_STAIRS),
    BRICK_WALL("brick_wall", () -> Items.BRICK_WALL), BRICKS("bricks", () -> Items.BRICKS),
    BROWN_BANNER("brown_banner", () -> Items.BROWN_BANNER), BROWN_BED("brown_bed", () -> Items.BROWN_BED),
    BROWN_CARPET("brown_carpet", () -> Items.BROWN_CARPET),
    BROWN_CONCRETE("brown_concrete", () -> Items.BROWN_CONCRETE),
    BROWN_CONCRETE_POWDER("brown_concrete_powder", () -> Items.BROWN_CONCRETE_POWDER),
    BROWN_DYE("brown_dye", () -> Items.BROWN_DYE),
    BROWN_GLAZED_TERRACOTTA("brown_glazed_terracotta", () -> Items.BROWN_GLAZED_TERRACOTTA),
    BROWN_MUSHROOM("brown_mushroom", () -> Items.BROWN_MUSHROOM),
    BROWN_MUSHROOM_BLOCK("brown_mushroom_block", () -> Items.BROWN_MUSHROOM_BLOCK),
    BROWN_SHULKER_BOX("brown_shulker_box", () -> Items.BROWN_SHULKER_BOX),
    BROWN_STAINED_GLASS("brown_stained_glass", () -> Items.BROWN_STAINED_GLASS),
    BROWN_STAINED_GLASS_PANE("brown_stained_glass_pane", () -> Items.BROWN_STAINED_GLASS_PANE),
    BROWN_TERRACOTTA("brown_terracotta", () -> Items.BROWN_TERRACOTTA),
    BROWN_WOOL("brown_wool", () -> Items.BROWN_WOOL), BUBBLE_CORAL("bubble_coral", () -> Items.BUBBLE_CORAL),
    BUBBLE_CORAL_BLOCK("bubble_coral_block", () -> Items.BUBBLE_CORAL_BLOCK),
    BUBBLE_CORAL_FAN("bubble_coral_fan", () -> Items.BUBBLE_CORAL_FAN), BUCKET("bucket", () -> Items.BUCKET),
    CACTUS("cactus", () -> Items.CACTUS), CAKE("cake", () -> Items.CAKE), CAMPFIRE("campfire", () -> Items.CAMPFIRE),
    CARROT("carrot", () -> Items.CARROT), CARROT_ON_A_STICK("carrot_on_a_stick", () -> Items.CARROT_ON_A_STICK),
    CARTOGRAPHY_TABLE("cartography_table", () -> Items.CARTOGRAPHY_TABLE),
    CARVED_PUMPKIN("carved_pumpkin", () -> Items.CARVED_PUMPKIN),
    CAT_SPAWN_EGG("cat_spawn_egg", () -> Items.CAT_SPAWN_EGG), CAULDRON("cauldron", () -> Items.CAULDRON),
    CAVE_SPIDER_SPAWN_EGG("cave_spider_spawn_egg", () -> Items.CAVE_SPIDER_SPAWN_EGG),
    CHAIN("chain", () -> Items.CHAIN), CHAIN_COMMAND_BLOCK("chain_command_block", () -> Items.CHAIN_COMMAND_BLOCK),
    CHAINMAIL_BOOTS("chainmail_boots", () -> Items.CHAINMAIL_BOOTS),
    CHAINMAIL_CHESTPLATE("chainmail_chestplate", () -> Items.CHAINMAIL_CHESTPLATE),
    CHAINMAIL_HELMET("chainmail_helmet", () -> Items.CHAINMAIL_HELMET),
    CHAINMAIL_LEGGINGS("chainmail_leggings", () -> Items.CHAINMAIL_LEGGINGS),
    CHARCOAL("charcoal", () -> Items.CHARCOAL), CHEST("chest", () -> Items.CHEST),
    CHEST_MINECART("chest_minecart", () -> Items.CHEST_MINECART), CHICKEN("chicken", () -> Items.CHICKEN),
    CHICKEN_SPAWN_EGG("chicken_spawn_egg", () -> Items.CHICKEN_SPAWN_EGG),
    CHIPPED_ANVIL("chipped_anvil", () -> Items.CHIPPED_ANVIL),
    CHISELED_NETHER_BRICKS("chiseled_nether_bricks", () -> Items.CHISELED_NETHER_BRICKS),
    CHISELED_POLISHED_BLACKSTONE("chiseled_polished_blackstone", () -> Items.CHISELED_POLISHED_BLACKSTONE),
    CHISELED_QUARTZ_BLOCK("chiseled_quartz_block", () -> Items.CHISELED_QUARTZ_BLOCK),
    CHISELED_RED_SANDSTONE("chiseled_red_sandstone", () -> Items.CHISELED_RED_SANDSTONE),
    CHISELED_SANDSTONE("chiseled_sandstone", () -> Items.CHISELED_SANDSTONE),
    CHISELED_STONE_BRICKS("chiseled_stone_bricks", () -> Items.CHISELED_STONE_BRICKS),
    CHORUS_FLOWER("chorus_flower", () -> Items.CHORUS_FLOWER), CHORUS_FRUIT("chorus_fruit", () -> Items.CHORUS_FRUIT),
    CHORUS_PLANT("chorus_plant", () -> Items.CHORUS_PLANT), CLAY("clay", () -> Items.CLAY),
    CLAY_BALL("clay_ball", () -> Items.CLAY_BALL), CLOCK("clock", () -> Items.CLOCK), COAL("coal", () -> Items.COAL),
    COAL_BLOCK("coal_block", () -> Items.COAL_BLOCK), COAL_ORE("coal_ore", () -> Items.COAL_ORE),
    COARSE_DIRT("coarse_dirt", () -> Items.COARSE_DIRT), COBBLESTONE("cobblestone", () -> Items.COBBLESTONE),
    COBBLESTONE_SLAB("cobblestone_slab", () -> Items.COBBLESTONE_SLAB),
    COBBLESTONE_STAIRS("cobblestone_stairs", () -> Items.COBBLESTONE_STAIRS),
    COBBLESTONE_WALL("cobblestone_wall", () -> Items.COBBLESTONE_WALL), COBWEB("cobweb", () -> Items.COBWEB),
    COCOA_BEANS("cocoa_beans", () -> Items.COCOA_BEANS), COD("cod", () -> Items.COD),
    COD_BUCKET("cod_bucket", () -> Items.COD_BUCKET), COD_SPAWN_EGG("cod_spawn_egg", () -> Items.COD_SPAWN_EGG),
    COMMAND_BLOCK("command_block", () -> Items.COMMAND_BLOCK),
    COMMAND_BLOCK_MINECART("command_block_minecart", () -> Items.COMMAND_BLOCK_MINECART),
    COMPARATOR("comparator", () -> Items.COMPARATOR), COMPASS("compass", () -> Items.COMPASS),
    COMPOSTER("composter", () -> Items.COMPOSTER), CONDUIT("conduit", () -> Items.CONDUIT),
    COOKED_BEEF("cooked_beef", () -> Items.COOKED_BEEF), COOKED_CHICKEN("cooked_chicken", () -> Items.COOKED_CHICKEN),
    COOKED_COD("cooked_cod", () -> Items.COOKED_COD), COOKED_MUTTON("cooked_mutton", () -> Items.COOKED_MUTTON),
    COOKED_PORKCHOP("cooked_porkchop", () -> Items.COOKED_PORKCHOP),
    COOKED_RABBIT("cooked_rabbit", () -> Items.COOKED_RABBIT),
    COOKED_SALMON("cooked_salmon", () -> Items.COOKED_SALMON), COOKIE("cookie", () -> Items.COOKIE),
    CORNFLOWER("cornflower", () -> Items.CORNFLOWER), COW_SPAWN_EGG("cow_spawn_egg", () -> Items.COW_SPAWN_EGG),
    CRACKED_NETHER_BRICKS("cracked_nether_bricks", () -> Items.CRACKED_NETHER_BRICKS),
    CRACKED_POLISHED_BLACKSTONE_BRICKS("cracked_polished_blackstone_bricks",
            () -> Items.CRACKED_POLISHED_BLACKSTONE_BRICKS),
    CRACKED_STONE_BRICKS("cracked_stone_bricks", () -> Items.CRACKED_STONE_BRICKS),
    CRAFTING_TABLE("crafting_table", () -> Items.CRAFTING_TABLE),
    CREEPER_BANNER_PATTERN("creeper_banner_pattern", () -> Items.CREEPER_BANNER_PATTERN),
    CREEPER_HEAD("creeper_head", () -> Items.CREEPER_HEAD),
    CREEPER_SPAWN_EGG("creeper_spawn_egg", () -> Items.CREEPER_SPAWN_EGG),
    CRIMSON_BUTTON("crimson_button", () -> Items.CRIMSON_BUTTON),
    CRIMSON_DOOR("crimson_door", () -> Items.CRIMSON_DOOR), CRIMSON_FENCE("crimson_fence", () -> Items.CRIMSON_FENCE),
    CRIMSON_FENCE_GATE("crimson_fence_gate", () -> Items.CRIMSON_FENCE_GATE),
    CRIMSON_FUNGUS("crimson_fungus", () -> Items.CRIMSON_FUNGUS),
    CRIMSON_HYPHAE("crimson_hyphae", () -> Items.CRIMSON_HYPHAE),
    CRIMSON_NYLIUM("crimson_nylium", () -> Items.CRIMSON_NYLIUM),
    CRIMSON_PLANKS("crimson_planks", () -> Items.CRIMSON_PLANKS),
    CRIMSON_PRESSURE_PLATE("crimson_pressure_plate", () -> Items.CRIMSON_PRESSURE_PLATE),
    CRIMSON_ROOTS("crimson_roots", () -> Items.CRIMSON_ROOTS), CRIMSON_SIGN("crimson_sign", () -> Items.CRIMSON_SIGN),
    CRIMSON_SLAB("crimson_slab", () -> Items.CRIMSON_SLAB),
    CRIMSON_STAIRS("crimson_stairs", () -> Items.CRIMSON_STAIRS),
    CRIMSON_STEM("crimson_stem", () -> Items.CRIMSON_STEM),
    CRIMSON_TRAPDOOR("crimson_trapdoor", () -> Items.CRIMSON_TRAPDOOR), CROSSBOW("crossbow", () -> Items.CROSSBOW),
    CRYING_OBSIDIAN("crying_obsidian", () -> Items.CRYING_OBSIDIAN),
    CUT_RED_SANDSTONE("cut_red_sandstone", () -> Items.CUT_RED_SANDSTONE),
    CUT_RED_SANDSTONE_SLAB("cut_red_sandstone_slab", () -> Items.CUT_RED_SANDSTONE_SLAB),
    CUT_SANDSTONE("cut_sandstone", () -> Items.CUT_SANDSTONE), CYAN_BANNER("cyan_banner", () -> Items.CYAN_BANNER),
    CYAN_BED("cyan_bed", () -> Items.CYAN_BED), CYAN_CARPET("cyan_carpet", () -> Items.CYAN_CARPET),
    CYAN_CONCRETE("cyan_concrete", () -> Items.CYAN_CONCRETE),
    CYAN_CONCRETE_POWDER("cyan_concrete_powder", () -> Items.CYAN_CONCRETE_POWDER),
    CYAN_DYE("cyan_dye", () -> Items.CYAN_DYE),
    CYAN_GLAZED_TERRACOTTA("cyan_glazed_terracotta", () -> Items.CYAN_GLAZED_TERRACOTTA),
    CYAN_SHULKER_BOX("cyan_shulker_box", () -> Items.CYAN_SHULKER_BOX),
    CYAN_STAINED_GLASS("cyan_stained_glass", () -> Items.CYAN_STAINED_GLASS),
    CYAN_STAINED_GLASS_PANE("cyan_stained_glass_pane", () -> Items.CYAN_STAINED_GLASS_PANE),
    CYAN_TERRACOTTA("cyan_terracotta", () -> Items.CYAN_TERRACOTTA), CYAN_WOOL("cyan_wool", () -> Items.CYAN_WOOL),
    DAMAGED_ANVIL("damaged_anvil", () -> Items.DAMAGED_ANVIL), DANDELION("dandelion", () -> Items.DANDELION),
    DARK_OAK_BOAT("dark_oak_boat", () -> Items.DARK_OAK_BOAT),
    DARK_OAK_BUTTON("dark_oak_button", () -> Items.DARK_OAK_BUTTON),
    DARK_OAK_DOOR("dark_oak_door", () -> Items.DARK_OAK_DOOR),
    DARK_OAK_FENCE("dark_oak_fence", () -> Items.DARK_OAK_FENCE),
    DARK_OAK_FENCE_GATE("dark_oak_fence_gate", () -> Items.DARK_OAK_FENCE_GATE),
    DARK_OAK_LEAVES("dark_oak_leaves", () -> Items.DARK_OAK_LEAVES),
    DARK_OAK_LOG("dark_oak_log", () -> Items.DARK_OAK_LOG),
    DARK_OAK_PLANKS("dark_oak_planks", () -> Items.DARK_OAK_PLANKS),
    DARK_OAK_PRESSURE_PLATE("dark_oak_pressure_plate", () -> Items.DARK_OAK_PRESSURE_PLATE),
    DARK_OAK_SAPLING("dark_oak_sapling", () -> Items.DARK_OAK_SAPLING),
    DARK_OAK_SIGN("dark_oak_sign", () -> Items.DARK_OAK_SIGN),
    DARK_OAK_SLAB("dark_oak_slab", () -> Items.DARK_OAK_SLAB),
    DARK_OAK_STAIRS("dark_oak_stairs", () -> Items.DARK_OAK_STAIRS),
    DARK_OAK_TRAPDOOR("dark_oak_trapdoor", () -> Items.DARK_OAK_TRAPDOOR),
    DARK_OAK_WOOD("dark_oak_wood", () -> Items.DARK_OAK_WOOD),
    DARK_PRISMARINE("dark_prismarine", () -> Items.DARK_PRISMARINE),
    DARK_PRISMARINE_SLAB("dark_prismarine_slab", () -> Items.DARK_PRISMARINE_SLAB),
    DARK_PRISMARINE_STAIRS("dark_prismarine_stairs", () -> Items.DARK_PRISMARINE_STAIRS),
    DAYLIGHT_DETECTOR("daylight_detector", () -> Items.DAYLIGHT_DETECTOR),
    DEAD_BRAIN_CORAL("dead_brain_coral", () -> Items.DEAD_BRAIN_CORAL),
    DEAD_BRAIN_CORAL_BLOCK("dead_brain_coral_block", () -> Items.DEAD_BRAIN_CORAL_BLOCK),
    DEAD_BRAIN_CORAL_FAN("dead_brain_coral_fan", () -> Items.DEAD_BRAIN_CORAL_FAN),
    DEAD_BUBBLE_CORAL("dead_bubble_coral", () -> Items.DEAD_BUBBLE_CORAL),
    DEAD_BUBBLE_CORAL_BLOCK("dead_bubble_coral_block", () -> Items.DEAD_BUBBLE_CORAL_BLOCK),
    DEAD_BUBBLE_CORAL_FAN("dead_bubble_coral_fan", () -> Items.DEAD_BUBBLE_CORAL_FAN),
    DEAD_BUSH("dead_bush", () -> Items.DEAD_BUSH), DEAD_FIRE_CORAL("dead_fire_coral", () -> Items.DEAD_FIRE_CORAL),
    DEAD_FIRE_CORAL_BLOCK("dead_fire_coral_block", () -> Items.DEAD_FIRE_CORAL_BLOCK),
    DEAD_FIRE_CORAL_FAN("dead_fire_coral_fan", () -> Items.DEAD_FIRE_CORAL_FAN),
    DEAD_HORN_CORAL("dead_horn_coral", () -> Items.DEAD_HORN_CORAL),
    DEAD_HORN_CORAL_BLOCK("dead_horn_coral_block", () -> Items.DEAD_HORN_CORAL_BLOCK),
    DEAD_HORN_CORAL_FAN("dead_horn_coral_fan", () -> Items.DEAD_HORN_CORAL_FAN),
    DEAD_TUBE_CORAL("dead_tube_coral", () -> Items.DEAD_TUBE_CORAL),
    DEAD_TUBE_CORAL_BLOCK("dead_tube_coral_block", () -> Items.DEAD_TUBE_CORAL_BLOCK),
    DEAD_TUBE_CORAL_FAN("dead_tube_coral_fan", () -> Items.DEAD_TUBE_CORAL_FAN),
    DEBUG_STICK("debug_stick", () -> Items.DEBUG_STICK), DETECTOR_RAIL("detector_rail", () -> Items.DETECTOR_RAIL),
    DIAMOND("diamond", () -> Items.DIAMOND), DIAMOND_AXE("diamond_axe", () -> Items.DIAMOND_AXE),
    DIAMOND_BLOCK("diamond_block", () -> Items.DIAMOND_BLOCK),
    DIAMOND_BOOTS("diamond_boots", () -> Items.DIAMOND_BOOTS),
    DIAMOND_CHESTPLATE("diamond_chestplate", () -> Items.DIAMOND_CHESTPLATE),
    DIAMOND_HELMET("diamond_helmet", () -> Items.DIAMOND_HELMET), DIAMOND_HOE("diamond_hoe", () -> Items.DIAMOND_HOE),
    DIAMOND_HORSE_ARMOR("diamond_horse_armor", () -> Items.DIAMOND_HORSE_ARMOR),
    DIAMOND_LEGGINGS("diamond_leggings", () -> Items.DIAMOND_LEGGINGS),
    DIAMOND_ORE("diamond_ore", () -> Items.DIAMOND_ORE),
    DIAMOND_PICKAXE("diamond_pickaxe", () -> Items.DIAMOND_PICKAXE),
    DIAMOND_SHOVEL("diamond_shovel", () -> Items.DIAMOND_SHOVEL),
    DIAMOND_SWORD("diamond_sword", () -> Items.DIAMOND_SWORD), DIORITE("diorite", () -> Items.DIORITE),
    DIORITE_SLAB("diorite_slab", () -> Items.DIORITE_SLAB),
    DIORITE_STAIRS("diorite_stairs", () -> Items.DIORITE_STAIRS),
    DIORITE_WALL("diorite_wall", () -> Items.DIORITE_WALL), DIRT("dirt", () -> Items.DIRT),
    DISPENSER("dispenser", () -> Items.DISPENSER),
    DOLPHIN_SPAWN_EGG("dolphin_spawn_egg", () -> Items.DOLPHIN_SPAWN_EGG),
    DONKEY_SPAWN_EGG("donkey_spawn_egg", () -> Items.DONKEY_SPAWN_EGG),
    DRAGON_BREATH("dragon_breath", () -> Items.DRAGON_BREATH), DRAGON_EGG("dragon_egg", () -> Items.DRAGON_EGG),
    DRAGON_HEAD("dragon_head", () -> Items.DRAGON_HEAD), DRIED_KELP("dried_kelp", () -> Items.DRIED_KELP),
    DRIED_KELP_BLOCK("dried_kelp_block", () -> Items.DRIED_KELP_BLOCK), DROPPER("dropper", () -> Items.DROPPER),
    DROWNED_SPAWN_EGG("drowned_spawn_egg", () -> Items.DROWNED_SPAWN_EGG), EGG("egg", () -> Items.EGG),
    ELDER_GUARDIAN_SPAWN_EGG("elder_guardian_spawn_egg", () -> Items.ELDER_GUARDIAN_SPAWN_EGG),
    ELYTRA("elytra", () -> Items.ELYTRA), EMERALD("emerald", () -> Items.EMERALD),
    EMERALD_BLOCK("emerald_block", () -> Items.EMERALD_BLOCK), EMERALD_ORE("emerald_ore", () -> Items.EMERALD_ORE),
    ENCHANTED_BOOK("enchanted_book", () -> Items.ENCHANTED_BOOK),
    ENCHANTED_GOLDEN_APPLE("enchanted_golden_apple", () -> Items.ENCHANTED_GOLDEN_APPLE),
    ENCHANTING_TABLE("enchanting_table", () -> Items.ENCHANTING_TABLE),
    END_CRYSTAL("end_crystal", () -> Items.END_CRYSTAL),
    END_PORTAL_FRAME("end_portal_frame", () -> Items.END_PORTAL_FRAME), END_ROD("end_rod", () -> Items.END_ROD),
    END_STONE("end_stone", () -> Items.END_STONE),
    END_STONE_BRICK_SLAB("end_stone_brick_slab", () -> Items.END_STONE_BRICK_SLAB),
    END_STONE_BRICK_STAIRS("end_stone_brick_stairs", () -> Items.END_STONE_BRICK_STAIRS),
    END_STONE_BRICK_WALL("end_stone_brick_wall", () -> Items.END_STONE_BRICK_WALL),
    END_STONE_BRICKS("end_stone_bricks", () -> Items.END_STONE_BRICKS),
    ENDER_CHEST("ender_chest", () -> Items.ENDER_CHEST), ENDER_EYE("ender_eye", () -> Items.ENDER_EYE),
    ENDER_PEARL("ender_pearl", () -> Items.ENDER_PEARL),
    ENDERMAN_SPAWN_EGG("enderman_spawn_egg", () -> Items.ENDERMAN_SPAWN_EGG),
    ENDERMITE_SPAWN_EGG("endermite_spawn_egg", () -> Items.ENDERMITE_SPAWN_EGG),
    EVOKER_SPAWN_EGG("evoker_spawn_egg", () -> Items.EVOKER_SPAWN_EGG),
    EXPERIENCE_BOTTLE("experience_bottle", () -> Items.EXPERIENCE_BOTTLE), FARMLAND("farmland", () -> Items.FARMLAND),
    FEATHER("feather", () -> Items.FEATHER),
    FERMENTED_SPIDER_EYE("fermented_spider_eye", () -> Items.FERMENTED_SPIDER_EYE), FERN("fern", () -> Items.FERN),
    FILLED_MAP("filled_map", () -> Items.FILLED_MAP), FIRE_CHARGE("fire_charge", () -> Items.FIRE_CHARGE),
    FIRE_CORAL("fire_coral", () -> Items.FIRE_CORAL),
    FIRE_CORAL_BLOCK("fire_coral_block", () -> Items.FIRE_CORAL_BLOCK),
    FIRE_CORAL_FAN("fire_coral_fan", () -> Items.FIRE_CORAL_FAN),
    FIREWORK_ROCKET("firework_rocket", () -> Items.FIREWORK_ROCKET),
    FIREWORK_STAR("firework_star", () -> Items.FIREWORK_STAR), FISHING_ROD("fishing_rod", () -> Items.FISHING_ROD),
    FLETCHING_TABLE("fletching_table", () -> Items.FLETCHING_TABLE), FLINT("flint", () -> Items.FLINT),
    FLINT_AND_STEEL("flint_and_steel", () -> Items.FLINT_AND_STEEL),
    FLOWER_BANNER_PATTERN("flower_banner_pattern", () -> Items.FLOWER_BANNER_PATTERN),
    FLOWER_POT("flower_pot", () -> Items.FLOWER_POT), FOX_SPAWN_EGG("fox_spawn_egg", () -> Items.FOX_SPAWN_EGG),
    FURNACE("furnace", () -> Items.FURNACE), FURNACE_MINECART("furnace_minecart", () -> Items.FURNACE_MINECART),
    GHAST_SPAWN_EGG("ghast_spawn_egg", () -> Items.GHAST_SPAWN_EGG), GHAST_TEAR("ghast_tear", () -> Items.GHAST_TEAR),
    GILDED_BLACKSTONE("gilded_blackstone", () -> Items.GILDED_BLACKSTONE), GLASS("glass", () -> Items.GLASS),
    GLASS_BOTTLE("glass_bottle", () -> Items.GLASS_BOTTLE), GLASS_PANE("glass_pane", () -> Items.GLASS_PANE),
    GLISTERING_MELON_SLICE("glistering_melon_slice", () -> Items.GLISTERING_MELON_SLICE),
    GLOBE_BANNER_PATTERN("globe_banner_pattern", () -> Items.GLOBE_BANNER_PATTER),
    GLOWSTONE("glowstone", () -> Items.GLOWSTONE), GLOWSTONE_DUST("glowstone_dust", () -> Items.GLOWSTONE_DUST),
    GOLD_BLOCK("gold_block", () -> Items.GOLD_BLOCK), GOLD_INGOT("gold_ingot", () -> Items.GOLD_INGOT),
    GOLD_NUGGET("gold_nugget", () -> Items.GOLD_NUGGET), GOLD_ORE("gold_ore", () -> Items.GOLD_ORE),
    GOLDEN_APPLE("golden_apple", () -> Items.GOLDEN_APPLE), GOLDEN_AXE("golden_axe", () -> Items.GOLDEN_AXE),
    GOLDEN_BOOTS("golden_boots", () -> Items.GOLDEN_BOOTS), GOLDEN_CARROT("golden_carrot", () -> Items.GOLDEN_CARROT),
    GOLDEN_CHESTPLATE("golden_chestplate", () -> Items.GOLDEN_CHESTPLATE),
    GOLDEN_HELMET("golden_helmet", () -> Items.GOLDEN_HELMET), GOLDEN_HOE("golden_hoe", () -> Items.GOLDEN_HOE),
    GOLDEN_HORSE_ARMOR("golden_horse_armor", () -> Items.GOLDEN_HORSE_ARMOR),
    GOLDEN_LEGGINGS("golden_leggings", () -> Items.GOLDEN_LEGGINGS),
    GOLDEN_PICKAXE("golden_pickaxe", () -> Items.GOLDEN_PICKAXE),
    GOLDEN_SHOVEL("golden_shovel", () -> Items.GOLDEN_SHOVEL), GOLDEN_SWORD("golden_sword", () -> Items.GOLDEN_SWORD),
    GRANITE("granite", () -> Items.GRANITE), GRANITE_SLAB("granite_slab", () -> Items.GRANITE_SLAB),
    GRANITE_STAIRS("granite_stairs", () -> Items.GRANITE_STAIRS),
    GRANITE_WALL("granite_wall", () -> Items.GRANITE_WALL), GRASS("grass", () -> Items.GRASS),
    GRASS_BLOCK("grass_block", () -> Items.GRASS_BLOCK), GRASS_PATH("grass_path", () -> Items.GRASS_PATH),
    GRAVEL("gravel", () -> Items.GRAVEL), GRAY_BANNER("gray_banner", () -> Items.GRAY_BANNER),
    GRAY_BED("gray_bed", () -> Items.GRAY_BED), GRAY_CARPET("gray_carpet", () -> Items.GRAY_CARPET),
    GRAY_CONCRETE("gray_concrete", () -> Items.GRAY_CONCRETE),
    GRAY_CONCRETE_POWDER("gray_concrete_powder", () -> Items.GRAY_CONCRETE_POWDER),
    GRAY_DYE("gray_dye", () -> Items.GRAY_DYE),
    GRAY_GLAZED_TERRACOTTA("gray_glazed_terracotta", () -> Items.GRAY_GLAZED_TERRACOTTA),
    GRAY_SHULKER_BOX("gray_shulker_box", () -> Items.GRAY_SHULKER_BOX),
    GRAY_STAINED_GLASS("gray_stained_glass", () -> Items.GRAY_STAINED_GLASS),
    GRAY_STAINED_GLASS_PANE("gray_stained_glass_pane", () -> Items.GRAY_STAINED_GLASS_PANE),
    GRAY_TERRACOTTA("gray_terracotta", () -> Items.GRAY_TERRACOTTA), GRAY_WOOL("gray_wool", () -> Items.GRAY_WOOL),
    GREEN_BANNER("green_banner", () -> Items.GREEN_BANNER), GREEN_BED("green_bed", () -> Items.GREEN_BED),
    GREEN_CARPET("green_carpet", () -> Items.GREEN_CARPET),
    GREEN_CONCRETE("green_concrete", () -> Items.GREEN_CONCRETE),
    GREEN_CONCRETE_POWDER("green_concrete_powder", () -> Items.GREEN_CONCRETE_POWDER),
    GREEN_DYE("green_dye", () -> Items.GREEN_DYE),
    GREEN_GLAZED_TERRACOTTA("green_glazed_terracotta", () -> Items.GREEN_GLAZED_TERRACOTTA),
    GREEN_SHULKER_BOX("green_shulker_box", () -> Items.GREEN_SHULKER_BOX),
    GREEN_STAINED_GLASS("green_stained_glass", () -> Items.GREEN_STAINED_GLASS),
    GREEN_STAINED_GLASS_PANE("green_stained_glass_pane", () -> Items.GREEN_STAINED_GLASS_PANE),
    GREEN_TERRACOTTA("green_terracotta", () -> Items.GREEN_TERRACOTTA),
    GREEN_WOOL("green_wool", () -> Items.GREEN_WOOL), GRINDSTONE("grindstone", () -> Items.GRINDSTONE),
    GUARDIAN_SPAWN_EGG("guardian_spawn_egg", () -> Items.GUARDIAN_SPAWN_EGG),
    GUNPOWDER("gunpowder", () -> Items.GUNPOWDER), HAY_BLOCK("hay_block", () -> Items.HAY_BLOCK),
    HEART_OF_THE_SEA("heart_of_the_sea", () -> Items.HEART_OF_THE_SEA),
    HEAVY_WEIGHTED_PRESSURE_PLATE("heavy_weighted_pressure_plate", () -> Items.HEAVY_WEIGHTED_PRESSURE_PLATE),
    HOGLIN_SPAWN_EGG("hoglin_spawn_egg", () -> Items.HOGLIN_SPAWN_EGG),
    HONEY_BLOCK("honey_block", () -> Items.HONEY_BLOCK), HONEY_BOTTLE("honey_bottle", () -> Items.HONEY_BOTTLE),
    HONEYCOMB("honeycomb", () -> Items.HONEYCOMB), HONEYCOMB_BLOCK("honeycomb_block", () -> Items.HONEYCOMB_BLOCK),
    HOPPER("hopper", () -> Items.HOPPER), HOPPER_MINECART("hopper_minecart", () -> Items.HOPPER_MINECART),
    HORN_CORAL("horn_coral", () -> Items.HORN_CORAL),
    HORN_CORAL_BLOCK("horn_coral_block", () -> Items.HORN_CORAL_BLOCK),
    HORN_CORAL_FAN("horn_coral_fan", () -> Items.HORN_CORAL_FAN),
    HORSE_SPAWN_EGG("horse_spawn_egg", () -> Items.HORSE_SPAWN_EGG),
    HUSK_SPAWN_EGG("husk_spawn_egg", () -> Items.HUSK_SPAWN_EGG), ICE("ice", () -> Items.ICE),
    INFESTED_CHISELED_STONE_BRICKS("infested_chiseled_stone_bricks", () -> Items.INFESTED_CHISELED_STONE_BRICKS),
    INFESTED_COBBLESTONE("infested_cobblestone", () -> Items.INFESTED_COBBLESTONE),
    INFESTED_CRACKED_STONE_BRICKS("infested_cracked_stone_bricks", () -> Items.INFESTED_CRACKED_STONE_BRICKS),
    INFESTED_MOSSY_STONE_BRICKS("infested_mossy_stone_bricks", () -> Items.INFESTED_MOSSY_STONE_BRICKS),
    INFESTED_STONE("infested_stone", () -> Items.INFESTED_STONE),
    INFESTED_STONE_BRICKS("infested_stone_bricks", () -> Items.INFESTED_STONE_BRICKS),
    INK_SAC("ink_sac", () -> Items.INK_SAC), IRON_AXE("iron_axe", () -> Items.IRON_AXE),
    IRON_BARS("iron_bars", () -> Items.IRON_BARS), IRON_BLOCK("iron_block", () -> Items.IRON_BLOCK),
    IRON_BOOTS("iron_boots", () -> Items.IRON_BOOTS), IRON_CHESTPLATE("iron_chestplate", () -> Items.IRON_CHESTPLATE),
    IRON_DOOR("iron_door", () -> Items.IRON_DOOR), IRON_HELMET("iron_helmet", () -> Items.IRON_HELMET),
    IRON_HOE("iron_hoe", () -> Items.IRON_HOE), IRON_HORSE_ARMOR("iron_horse_armor", () -> Items.IRON_HORSE_ARMOR),
    IRON_INGOT("iron_ingot", () -> Items.IRON_INGOT), IRON_LEGGINGS("iron_leggings", () -> Items.IRON_LEGGINGS),
    IRON_NUGGET("iron_nugget", () -> Items.IRON_NUGGET), IRON_ORE("iron_ore", () -> Items.IRON_ORE),
    IRON_PICKAXE("iron_pickaxe", () -> Items.IRON_PICKAXE), IRON_SHOVEL("iron_shovel", () -> Items.IRON_SHOVEL),
    IRON_SWORD("iron_sword", () -> Items.IRON_SWORD), IRON_TRAPDOOR("iron_trapdoor", () -> Items.IRON_TRAPDOOR),
    ITEM_FRAME("item_frame", () -> Items.ITEM_FRAME), JACK_O_LANTERN("jack_o_lantern", () -> Items.JACK_O_LANTERN),
    JIGSAW("jigsaw", () -> Items.JIGSAW), JUKEBOX("jukebox", () -> Items.JUKEBOX),
    JUNGLE_BOAT("jungle_boat", () -> Items.JUNGLE_BOAT), JUNGLE_BUTTON("jungle_button", () -> Items.JUNGLE_BUTTON),
    JUNGLE_DOOR("jungle_door", () -> Items.JUNGLE_DOOR), JUNGLE_FENCE("jungle_fence", () -> Items.JUNGLE_FENCE),
    JUNGLE_FENCE_GATE("jungle_fence_gate", () -> Items.JUNGLE_FENCE_GATE),
    JUNGLE_LEAVES("jungle_leaves", () -> Items.JUNGLE_LEAVES), JUNGLE_LOG("jungle_log", () -> Items.JUNGLE_LOG),
    JUNGLE_PLANKS("jungle_planks", () -> Items.JUNGLE_PLANKS),
    JUNGLE_PRESSURE_PLATE("jungle_pressure_plate", () -> Items.JUNGLE_PRESSURE_PLATE),
    JUNGLE_SAPLING("jungle_sapling", () -> Items.JUNGLE_SAPLING), JUNGLE_SIGN("jungle_sign", () -> Items.JUNGLE_SIGN),
    JUNGLE_SLAB("jungle_slab", () -> Items.JUNGLE_SLAB), JUNGLE_STAIRS("jungle_stairs", () -> Items.JUNGLE_STAIRS),
    JUNGLE_TRAPDOOR("jungle_trapdoor", () -> Items.JUNGLE_TRAPDOOR),
    JUNGLE_WOOD("jungle_wood", () -> Items.JUNGLE_WOOD), KELP("kelp", () -> Items.KELP),
    KNOWLEDGE_BOOK("knowledge_book", () -> Items.KNOWLEDGE_BOOK), LADDER("ladder", () -> Items.LADDER),
    LANTERN("lantern", () -> Items.LANTERN), LAPIS_BLOCK("lapis_block", () -> Items.LAPIS_BLOCK),
    LAPIS_LAZULI("lapis_lazuli", () -> Items.LAPIS_LAZULI), LAPIS_ORE("lapis_ore", () -> Items.LAPIS_ORE),
    LARGE_FERN("large_fern", () -> Items.LARGE_FERN), LAVA_BUCKET("lava_bucket", () -> Items.LAVA_BUCKET),
    LEAD("lead", () -> Items.LEAD), LEATHER("leather", () -> Items.LEATHER),
    LEATHER_BOOTS("leather_boots", () -> Items.LEATHER_BOOTS),
    LEATHER_CHESTPLATE("leather_chestplate", () -> Items.LEATHER_CHESTPLATE),
    LEATHER_HELMET("leather_helmet", () -> Items.LEATHER_HELMET),
    LEATHER_HORSE_ARMOR("leather_horse_armor", () -> Items.LEATHER_HORSE_ARMOR),
    LEATHER_LEGGINGS("leather_leggings", () -> Items.LEATHER_LEGGINGS), LECTERN("lectern", () -> Items.LECTERN),
    LEVER("lever", () -> Items.LEVER), LIGHT_BLUE_BANNER("light_blue_banner", () -> Items.LIGHT_BLUE_BANNER),
    LIGHT_BLUE_BED("light_blue_bed", () -> Items.LIGHT_BLUE_BED),
    LIGHT_BLUE_CARPET("light_blue_carpet", () -> Items.LIGHT_BLUE_CARPET),
    LIGHT_BLUE_CONCRETE("light_blue_concrete", () -> Items.LIGHT_BLUE_CONCRETE),
    LIGHT_BLUE_CONCRETE_POWDER("light_blue_concrete_powder", () -> Items.LIGHT_BLUE_CONCRETE_POWDER),
    LIGHT_BLUE_DYE("light_blue_dye", () -> Items.LIGHT_BLUE_DYE),
    LIGHT_BLUE_GLAZED_TERRACOTTA("light_blue_glazed_terracotta", () -> Items.LIGHT_BLUE_GLAZED_TERRACOTTA),
    LIGHT_BLUE_SHULKER_BOX("light_blue_shulker_box", () -> Items.LIGHT_BLUE_SHULKER_BOX),
    LIGHT_BLUE_STAINED_GLASS("light_blue_stained_glass", () -> Items.LIGHT_BLUE_STAINED_GLASS),
    LIGHT_BLUE_STAINED_GLASS_PANE("light_blue_stained_glass_pane", () -> Items.LIGHT_BLUE_STAINED_GLASS_PANE),
    LIGHT_BLUE_TERRACOTTA("light_blue_terracotta", () -> Items.LIGHT_BLUE_TERRACOTTA),
    LIGHT_BLUE_WOOL("light_blue_wool", () -> Items.LIGHT_BLUE_WOOL),
    LIGHT_GRAY_BANNER("light_gray_banner", () -> Items.LIGHT_GRAY_BANNER),
    LIGHT_GRAY_BED("light_gray_bed", () -> Items.LIGHT_GRAY_BED),
    LIGHT_GRAY_CARPET("light_gray_carpet", () -> Items.LIGHT_GRAY_CARPET),
    LIGHT_GRAY_CONCRETE("light_gray_concrete", () -> Items.LIGHT_GRAY_CONCRETE),
    LIGHT_GRAY_CONCRETE_POWDER("light_gray_concrete_powder", () -> Items.LIGHT_GRAY_CONCRETE_POWDER),
    LIGHT_GRAY_DYE("light_gray_dye", () -> Items.LIGHT_GRAY_DYE),
    LIGHT_GRAY_GLAZED_TERRACOTTA("light_gray_glazed_terracotta", () -> Items.LIGHT_GRAY_GLAZED_TERRACOTTA),
    LIGHT_GRAY_SHULKER_BOX("light_gray_shulker_box", () -> Items.LIGHT_GRAY_SHULKER_BOX),
    LIGHT_GRAY_STAINED_GLASS("light_gray_stained_glass", () -> Items.LIGHT_GRAY_STAINED_GLASS),
    LIGHT_GRAY_STAINED_GLASS_PANE("light_gray_stained_glass_pane", () -> Items.LIGHT_GRAY_STAINED_GLASS_PANE),
    LIGHT_GRAY_TERRACOTTA("light_gray_terracotta", () -> Items.LIGHT_GRAY_TERRACOTTA),
    LIGHT_GRAY_WOOL("light_gray_wool", () -> Items.LIGHT_GRAY_WOOL),
    LIGHT_WEIGHTED_PRESSURE_PLATE("light_weighted_pressure_plate", () -> Items.LIGHT_WEIGHTED_PRESSURE_PLATE),
    LILAC("lilac", () -> Items.LILAC), LILY_OF_THE_VALLEY("lily_of_the_valley", () -> Items.LILY_OF_THE_VALLEY),
    LILY_PAD("lily_pad", () -> Items.LILY_PAD), LIME_BANNER("lime_banner", () -> Items.LIME_BANNER),
    LIME_BED("lime_bed", () -> Items.LIME_BED), LIME_CARPET("lime_carpet", () -> Items.LIME_CARPET),
    LIME_CONCRETE("lime_concrete", () -> Items.LIME_CONCRETE),
    LIME_CONCRETE_POWDER("lime_concrete_powder", () -> Items.LIME_CONCRETE_POWDER),
    LIME_DYE("lime_dye", () -> Items.LIME_DYE),
    LIME_GLAZED_TERRACOTTA("lime_glazed_terracotta", () -> Items.LIME_GLAZED_TERRACOTTA),
    LIME_SHULKER_BOX("lime_shulker_box", () -> Items.LIME_SHULKER_BOX),
    LIME_STAINED_GLASS("lime_stained_glass", () -> Items.LIME_STAINED_GLASS),
    LIME_STAINED_GLASS_PANE("lime_stained_glass_pane", () -> Items.LIME_STAINED_GLASS_PANE),
    LIME_TERRACOTTA("lime_terracotta", () -> Items.LIME_TERRACOTTA), LIME_WOOL("lime_wool", () -> Items.LIME_WOOL),
    LINGERING_POTION("lingering_potion", () -> Items.LINGERING_POTION),
    LLAMA_SPAWN_EGG("llama_spawn_egg", () -> Items.LLAMA_SPAWN_EGG), LODESTONE("lodestone", () -> Items.LODESTONE),
    LOOM("loom", () -> Items.LOOM), MAGENTA_BANNER("magenta_banner", () -> Items.MAGENTA_BANNER),
    MAGENTA_BED("magenta_bed", () -> Items.MAGENTA_BED), MAGENTA_CARPET("magenta_carpet", () -> Items.MAGENTA_CARPET),
    MAGENTA_CONCRETE("magenta_concrete", () -> Items.MAGENTA_CONCRETE),
    MAGENTA_CONCRETE_POWDER("magenta_concrete_powder", () -> Items.MAGENTA_CONCRETE_POWDER),
    MAGENTA_DYE("magenta_dye", () -> Items.MAGENTA_DYE),
    MAGENTA_GLAZED_TERRACOTTA("magenta_glazed_terracotta", () -> Items.MAGENTA_GLAZED_TERRACOTTA),
    MAGENTA_SHULKER_BOX("magenta_shulker_box", () -> Items.MAGENTA_SHULKER_BOX),
    MAGENTA_STAINED_GLASS("magenta_stained_glass", () -> Items.MAGENTA_STAINED_GLASS),
    MAGENTA_STAINED_GLASS_PANE("magenta_stained_glass_pane", () -> Items.MAGENTA_STAINED_GLASS_PANE),
    MAGENTA_TERRACOTTA("magenta_terracotta", () -> Items.MAGENTA_TERRACOTTA),
    MAGENTA_WOOL("magenta_wool", () -> Items.MAGENTA_WOOL), MAGMA_BLOCK("magma_block", () -> Items.MAGMA_BLOCK),
    MAGMA_CREAM("magma_cream", () -> Items.MAGMA_CREAM),
    MAGMA_CUBE_SPAWN_EGG("magma_cube_spawn_egg", () -> Items.MAGMA_CUBE_SPAWN_EGG), MAP("map", () -> Items.MAP),
    MELON("melon", () -> Items.MELON), MELON_SEEDS("melon_seeds", () -> Items.MELON_SEEDS),
    MELON_SLICE("melon_slice", () -> Items.MELON_SLICE), MILK_BUCKET("milk_bucket", () -> Items.MILK_BUCKET),
    MINECART("minecart", () -> Items.MINECART),
    MOJANG_BANNER_PATTERN("mojang_banner_pattern", () -> Items.MOJANG_BANNER_PATTERN),
    MOOSHROOM_SPAWN_EGG("mooshroom_spawn_egg", () -> Items.MOOSHROOM_SPAWN_EGG),
    MOSSY_COBBLESTONE("mossy_cobblestone", () -> Items.MOSSY_COBBLESTONE),
    MOSSY_COBBLESTONE_SLAB("mossy_cobblestone_slab", () -> Items.MOSSY_COBBLESTONE_SLAB),
    MOSSY_COBBLESTONE_STAIRS("mossy_cobblestone_stairs", () -> Items.MOSSY_COBBLESTONE_STAIRS),
    MOSSY_COBBLESTONE_WALL("mossy_cobblestone_wall", () -> Items.MOSSY_COBBLESTONE_WALL),
    MOSSY_STONE_BRICK_SLAB("mossy_stone_brick_slab", () -> Items.MOSSY_STONE_BRICK_SLAB),
    MOSSY_STONE_BRICK_STAIRS("mossy_stone_brick_stairs", () -> Items.MOSSY_STONE_BRICK_STAIRS),
    MOSSY_STONE_BRICK_WALL("mossy_stone_brick_wall", () -> Items.MOSSY_STONE_BRICK_WALL),
    MOSSY_STONE_BRICKS("mossy_stone_bricks", () -> Items.MOSSY_STONE_BRICKS),
    MULE_SPAWN_EGG("mule_spawn_egg", () -> Items.MULE_SPAWN_EGG),
    MUSHROOM_STEM("mushroom_stem", () -> Items.MUSHROOM_STEM),
    MUSHROOM_STEW("mushroom_stew", () -> Items.MUSHROOM_STEW),
    MUSIC_DISC_11("music_disc_11", () -> Items.MUSIC_DISC_11),
    MUSIC_DISC_13("music_disc_13", () -> Items.MUSIC_DISC_13),
    MUSIC_DISC_BLOCKS("music_disc_blocks", () -> Items.MUSIC_DISC_BLOCKS),
    MUSIC_DISC_CAT("music_disc_cat", () -> Items.MUSIC_DISC_CAT),
    MUSIC_DISC_CHIRP("music_disc_chirp", () -> Items.MUSIC_DISC_CHIRP),
    MUSIC_DISC_FAR("music_disc_far", () -> Items.MUSIC_DISC_FAR),
    MUSIC_DISC_MALL("music_disc_mall", () -> Items.MUSIC_DISC_MALL),
    MUSIC_DISC_MELLOHI("music_disc_mellohi", () -> Items.MUSIC_DISC_MELLOHI),
    MUSIC_DISC_PIGSTEP("music_disc_pigstep", () -> Items.MUSIC_DISC_PIGSTEP),
    MUSIC_DISC_STAL("music_disc_stal", () -> Items.MUSIC_DISC_STAL),
    MUSIC_DISC_STRAD("music_disc_strad", () -> Items.MUSIC_DISC_STRAD),
    MUSIC_DISC_WAIT("music_disc_wait", () -> Items.MUSIC_DISC_WAIT),
    MUSIC_DISC_WARD("music_disc_ward", () -> Items.MUSIC_DISC_WARD), MUTTON("mutton", () -> Items.MUTTON),
    MYCELIUM("mycelium", () -> Items.MYCELIUM), NAME_TAG("name_tag", () -> Items.NAME_TAG),
    NAUTILUS_SHELL("nautilus_shell", () -> Items.NAUTILUS_SHELL),
    NETHER_BRICK("nether_brick", () -> Items.NETHER_BRICK),
    NETHER_BRICK_FENCE("nether_brick_fence", () -> Items.NETHER_BRICK_FENCE),
    NETHER_BRICK_SLAB("nether_brick_slab", () -> Items.NETHER_BRICK_SLAB),
    NETHER_BRICK_STAIRS("nether_brick_stairs", () -> Items.NETHER_BRICK_STAIRS),
    NETHER_BRICK_WALL("nether_brick_wall", () -> Items.NETHER_BRICK_WALL),
    NETHER_BRICKS("nether_bricks", () -> Items.NETHER_BRICKS),
    NETHER_GOLD_ORE("nether_gold_ore", () -> Items.NETHER_GOLD_ORE),
    NETHER_QUARTZ_ORE("nether_quartz_ore", () -> Items.NETHER_QUARTZ_ORE),
    NETHER_SPROUTS("nether_sprouts", () -> Items.NETHER_SPROUTS), NETHER_STAR("nether_star", () -> Items.NETHER_STAR),
    NETHER_WART("nether_wart", () -> Items.NETHER_WART),
    NETHER_WART_BLOCK("nether_wart_block", () -> Items.NETHER_WART_BLOCK),
    NETHERITE_AXE("netherite_axe", () -> Items.NETHERITE_AXE),
    NETHERITE_BLOCK("netherite_block", () -> Items.NETHERITE_BLOCK),
    NETHERITE_BOOTS("netherite_boots", () -> Items.NETHERITE_BOOTS),
    NETHERITE_CHESTPLATE("netherite_chestplate", () -> Items.NETHERITE_CHESTPLATE),
    NETHERITE_HELMET("netherite_helmet", () -> Items.NETHERITE_HELMET),
    NETHERITE_HOE("netherite_hoe", () -> Items.NETHERITE_HOE),
    NETHERITE_INGOT("netherite_ingot", () -> Items.NETHERITE_INGOT),
    NETHERITE_LEGGINGS("netherite_leggings", () -> Items.NETHERITE_LEGGINGS),
    NETHERITE_PICKAXE("netherite_pickaxe", () -> Items.NETHERITE_PICKAXE),
    NETHERITE_SCRAP("netherite_scrap", () -> Items.NETHERITE_SCRAP),
    NETHERITE_SHOVEL("netherite_shovel", () -> Items.NETHERITE_SHOVEL),
    NETHERITE_SWORD("netherite_sword", () -> Items.NETHERITE_SWORD), NETHERRACK("netherrack", () -> Items.NETHERRACK),
    NOTE_BLOCK("note_block", () -> Items.NOTE_BLOCK), OAK_BOAT("oak_boat", () -> Items.OAK_BOAT),
    OAK_BUTTON("oak_button", () -> Items.OAK_BUTTON), OAK_DOOR("oak_door", () -> Items.OAK_DOOR),
    OAK_FENCE("oak_fence", () -> Items.OAK_FENCE), OAK_FENCE_GATE("oak_fence_gate", () -> Items.OAK_FENCE_GATE),
    OAK_LEAVES("oak_leaves", () -> Items.OAK_LEAVES), OAK_LOG("oak_log", () -> Items.OAK_LOG),
    OAK_PLANKS("oak_planks", () -> Items.OAK_PLANKS),
    OAK_PRESSURE_PLATE("oak_pressure_plate", () -> Items.OAK_PRESSURE_PLATE),
    OAK_SAPLING("oak_sapling", () -> Items.OAK_SAPLING), OAK_SIGN("oak_sign", () -> Items.OAK_SIGN),
    OAK_SLAB("oak_slab", () -> Items.OAK_SLAB), OAK_STAIRS("oak_stairs", () -> Items.OAK_STAIRS),
    OAK_TRAPDOOR("oak_trapdoor", () -> Items.OAK_TRAPDOOR), OAK_WOOD("oak_wood", () -> Items.OAK_WOOD),
    OBSERVER("observer", () -> Items.OBSERVER), OBSIDIAN("obsidian", () -> Items.OBSIDIAN),
    OCELOT_SPAWN_EGG("ocelot_spawn_egg", () -> Items.OCELOT_SPAWN_EGG),
    ORANGE_BANNER("orange_banner", () -> Items.ORANGE_BANNER), ORANGE_BED("orange_bed", () -> Items.ORANGE_BED),
    ORANGE_CARPET("orange_carpet", () -> Items.ORANGE_CARPET),
    ORANGE_CONCRETE("orange_concrete", () -> Items.ORANGE_CONCRETE),
    ORANGE_CONCRETE_POWDER("orange_concrete_powder", () -> Items.ORANGE_CONCRETE_POWDER),
    ORANGE_DYE("orange_dye", () -> Items.ORANGE_DYE),
    ORANGE_GLAZED_TERRACOTTA("orange_glazed_terracotta", () -> Items.ORANGE_GLAZED_TERRACOTTA),
    ORANGE_SHULKER_BOX("orange_shulker_box", () -> Items.ORANGE_SHULKER_BOX),
    ORANGE_STAINED_GLASS("orange_stained_glass", () -> Items.ORANGE_STAINED_GLASS),
    ORANGE_STAINED_GLASS_PANE("orange_stained_glass_pane", () -> Items.ORANGE_STAINED_GLASS_PANE),
    ORANGE_TERRACOTTA("orange_terracotta", () -> Items.ORANGE_TERRACOTTA),
    ORANGE_TULIP("orange_tulip", () -> Items.ORANGE_TULIP), ORANGE_WOOL("orange_wool", () -> Items.ORANGE_WOOL),
    OXEYE_DAISY("oxeye_daisy", () -> Items.OXEYE_DAISY), PACKED_ICE("packed_ice", () -> Items.PACKED_ICE),
    PAINTING("painting", () -> Items.PAINTING), PANDA_SPAWN_EGG("panda_spawn_egg", () -> Items.PANDA_SPAWN_EGG),
    PAPER("paper", () -> Items.PAPER), PARROT_SPAWN_EGG("parrot_spawn_egg", () -> Items.PARROT_SPAWN_EGG),
    PEONY("peony", () -> Items.PEONY), PETRIFIED_OAK_SLAB("petrified_oak_slab", () -> Items.PETRIFIED_OAK_SLAB),
    PHANTOM_MEMBRANE("phantom_membrane", () -> Items.PHANTOM_MEMBRANE),
    PHANTOM_SPAWN_EGG("phantom_spawn_egg", () -> Items.PHANTOM_SPAWN_EGG),
    PIG_SPAWN_EGG("pig_spawn_egg", () -> Items.PIG_SPAWN_EGG),
    PIGLIN_BANNER_PATTERN("piglin_banner_pattern", () -> Items.PIGLIN_BANNER_PATTERN),
    PIGLIN_BRUTE_SPAWN_EGG("piglin_brute_spawn_egg", () -> Items.PIGLIN_BRUTE_SPAWN_EGG),
    PIGLIN_SPAWN_EGG("piglin_spawn_egg", () -> Items.PIGLIN_SPAWN_EGG),
    PILLAGER_SPAWN_EGG("pillager_spawn_egg", () -> Items.PILLAGER_SPAWN_EGG),
    PINK_BANNER("pink_banner", () -> Items.PINK_BANNER), PINK_BED("pink_bed", () -> Items.PINK_BED),
    PINK_CARPET("pink_carpet", () -> Items.PINK_CARPET), PINK_CONCRETE("pink_concrete", () -> Items.PINK_CONCRETE),
    PINK_CONCRETE_POWDER("pink_concrete_powder", () -> Items.PINK_CONCRETE_POWDER),
    PINK_DYE("pink_dye", () -> Items.PINK_DYE),
    PINK_GLAZED_TERRACOTTA("pink_glazed_terracotta", () -> Items.PINK_GLAZED_TERRACOTTA),
    PINK_SHULKER_BOX("pink_shulker_box", () -> Items.PINK_SHULKER_BOX),
    PINK_STAINED_GLASS("pink_stained_glass", () -> Items.PINK_STAINED_GLASS),
    PINK_STAINED_GLASS_PANE("pink_stained_glass_pane", () -> Items.PINK_STAINED_GLASS_PANE),
    PINK_TERRACOTTA("pink_terracotta", () -> Items.PINK_TERRACOTTA), PINK_TULIP("pink_tulip", () -> Items.PINK_TULIP),
    PINK_WOOL("pink_wool", () -> Items.PINK_WOOL), PISTON("piston", () -> Items.PISTON),
    PLAYER_HEAD("player_head", () -> Items.PLAYER_HEAD), PODZOL("podzol", () -> Items.PODZOL),
    POISONOUS_POTATO("poisonous_potato", () -> Items.POISONOUS_POTATO),
    POLAR_BEAR_SPAWN_EGG("polar_bear_spawn_egg", () -> Items.POLAR_BEAR_SPAWN_EGG),
    POLISHED_ANDESITE("polished_andesite", () -> Items.POLISHED_ANDESITE),
    POLISHED_ANDESITE_SLAB("polished_andesite_slab", () -> Items.POLISHED_ANDESITE_SLAB),
    POLISHED_ANDESITE_STAIRS("polished_andesite_stairs", () -> Items.POLISHED_ANDESITE_STAIRS),
    POLISHED_BASALT("polished_basalt", () -> Items.POLISHED_BASALT),
    POLISHED_BLACKSTONE("polished_blackstone", () -> Items.POLISHED_BLACKSTONE),
    POLISHED_BLACKSTONE_BRICK_SLAB("polished_blackstone_brick_slab", () -> Items.POLISHED_BLACKSTONE_BRICK_SLAB),
    POLISHED_BLACKSTONE_BRICK_STAIRS("polished_blackstone_brick_stairs", () -> Items.POLISHED_BLACKSTONE_BRICK_STAIRS),
    POLISHED_BLACKSTONE_BRICK_WALL("polished_blackstone_brick_wall", () -> Items.POLISHED_BLACKSTONE_BRICK_WALL),
    POLISHED_BLACKSTONE_BRICKS("polished_blackstone_bricks", () -> Items.POLISHED_BLACKSTONE_BRICKS),
    POLISHED_BLACKSTONE_BUTTON("polished_blackstone_button", () -> Items.POLISHED_BLACKSTONE_BUTTON),
    POLISHED_BLACKSTONE_PRESSURE_PLATE("polished_blackstone_pressure_plate",
            () -> Items.POLISHED_BLACKSTONE_PRESSURE_PLATE),
    POLISHED_BLACKSTONE_SLAB("polished_blackstone_slab", () -> Items.POLISHED_BLACKSTONE_SLAB),
    POLISHED_BLACKSTONE_STAIRS("polished_blackstone_stairs", () -> Items.POLISHED_BLACKSTONE_STAIRS),
    POLISHED_BLACKSTONE_WALL("polished_blackstone_wall", () -> Items.POLISHED_BLACKSTONE_WALL),
    POLISHED_DIORITE("polished_diorite", () -> Items.POLISHED_DIORITE),
    POLISHED_DIORITE_SLAB("polished_diorite_slab", () -> Items.POLISHED_DIORITE_SLAB),
    POLISHED_DIORITE_STAIRS("polished_diorite_stairs", () -> Items.POLISHED_DIORITE_STAIRS),
    POLISHED_GRANITE("polished_granite", () -> Items.POLISHED_GRANITE),
    POLISHED_GRANITE_SLAB("polished_granite_slab", () -> Items.POLISHED_GRANITE_SLAB),
    POLISHED_GRANITE_STAIRS("polished_granite_stairs", () -> Items.POLISHED_GRANITE_STAIRS),
    POPPED_CHORUS_FRUIT("popped_chorus_fruit", () -> Items.POPPED_CHORUS_FRUIT), POPPY("poppy", () -> Items.POPPY),
    PORKCHOP("porkchop", () -> Items.PORKCHOP), POTATO("potato", () -> Items.POTATO),
    POTION("potion", () -> Items.POTION), POWERED_RAIL("powered_rail", () -> Items.POWERED_RAIL),
    PRISMARINE("prismarine", () -> Items.PRISMARINE),
    PRISMARINE_BRICK_SLAB("prismarine_brick_slab", () -> Items.PRISMARINE_BRICK_SLAB),
    PRISMARINE_BRICK_STAIRS("prismarine_brick_stairs", () -> Items.PRISMARINE_BRICK_STAIRS),
    PRISMARINE_BRICKS("prismarine_bricks", () -> Items.PRISMARINE_BRICKS),
    PRISMARINE_CRYSTALS("prismarine_crystals", () -> Items.PRISMARINE_CRYSTALS),
    PRISMARINE_SHARD("prismarine_shard", () -> Items.PRISMARINE_SHARD),
    PRISMARINE_SLAB("prismarine_slab", () -> Items.PRISMARINE_SLAB),
    PRISMARINE_STAIRS("prismarine_stairs", () -> Items.PRISMARINE_STAIRS),
    PRISMARINE_WALL("prismarine_wall", () -> Items.PRISMARINE_WALL), PUFFERFISH("pufferfish", () -> Items.PUFFERFISH),
    PUFFERFISH_BUCKET("pufferfish_bucket", () -> Items.PUFFERFISH_BUCKET),
    PUFFERFISH_SPAWN_EGG("pufferfish_spawn_egg", () -> Items.PUFFERFISH_SPAWN_EGG),
    PUMPKIN("pumpkin", () -> Items.PUMPKIN), PUMPKIN_PIE("pumpkin_pie", () -> Items.PUMPKIN_PIE),
    PUMPKIN_SEEDS("pumpkin_seeds", () -> Items.PUMPKIN_SEEDS),
    PURPLE_BANNER("purple_banner", () -> Items.PURPLE_BANNER), PURPLE_BED("purple_bed", () -> Items.PURPLE_BED),
    PURPLE_CARPET("purple_carpet", () -> Items.PURPLE_CARPET),
    PURPLE_CONCRETE("purple_concrete", () -> Items.PURPLE_CONCRETE),
    PURPLE_CONCRETE_POWDER("purple_concrete_powder", () -> Items.PURPLE_CONCRETE_POWDER),
    PURPLE_DYE("purple_dye", () -> Items.PURPLE_DYE),
    PURPLE_GLAZED_TERRACOTTA("purple_glazed_terracotta", () -> Items.PURPLE_GLAZED_TERRACOTTA),
    PURPLE_SHULKER_BOX("purple_shulker_box", () -> Items.PURPLE_SHULKER_BOX),
    PURPLE_STAINED_GLASS("purple_stained_glass", () -> Items.PURPLE_STAINED_GLASS),
    PURPLE_STAINED_GLASS_PANE("purple_stained_glass_pane", () -> Items.PURPLE_STAINED_GLASS_PANE),
    PURPLE_TERRACOTTA("purple_terracotta", () -> Items.PURPLE_TERRACOTTA),
    PURPLE_WOOL("purple_wool", () -> Items.PURPLE_WOOL), PURPUR_BLOCK("purpur_block", () -> Items.PURPUR_BLOCK),
    PURPUR_PILLAR("purpur_pillar", () -> Items.PURPUR_PILLAR), PURPUR_SLAB("purpur_slab", () -> Items.PURPUR_SLAB),
    PURPUR_STAIRS("purpur_stairs", () -> Items.PURPUR_STAIRS), QUARTZ("quartz", () -> Items.QUARTZ),
    QUARTZ_BLOCK("quartz_block", () -> Items.QUARTZ_BLOCK), QUARTZ_BRICKS("quartz_bricks", () -> Items.QUARTZ_BRICKS),
    QUARTZ_PILLAR("quartz_pillar", () -> Items.QUARTZ_PILLAR), QUARTZ_SLAB("quartz_slab", () -> Items.QUARTZ_SLAB),
    QUARTZ_STAIRS("quartz_stairs", () -> Items.QUARTZ_STAIRS), RABBIT("rabbit", () -> Items.RABBIT),
    RABBIT_FOOT("rabbit_foot", () -> Items.RABBIT_FOOT), RABBIT_HIDE("rabbit_hide", () -> Items.RABBIT_HIDE),
    RABBIT_SPAWN_EGG("rabbit_spawn_egg", () -> Items.RABBIT_SPAWN_EGG),
    RABBIT_STEW("rabbit_stew", () -> Items.RABBIT_STEW), RAIL("rail", () -> Items.RAIL),
    RAVAGER_SPAWN_EGG("ravager_spawn_egg", () -> Items.RAVAGER_SPAWN_EGG),
    RED_BANNER("red_banner", () -> Items.RED_BANNER), RED_BED("red_bed", () -> Items.RED_BED),
    RED_CARPET("red_carpet", () -> Items.RED_CARPET), RED_CONCRETE("red_concrete", () -> Items.RED_CONCRETE),
    RED_CONCRETE_POWDER("red_concrete_powder", () -> Items.RED_CONCRETE_POWDER),
    RED_DYE("red_dye", () -> Items.RED_DYE),
    RED_GLAZED_TERRACOTTA("red_glazed_terracotta", () -> Items.RED_GLAZED_TERRACOTTA),
    RED_MUSHROOM("red_mushroom", () -> Items.RED_MUSHROOM),
    RED_MUSHROOM_BLOCK("red_mushroom_block", () -> Items.RED_MUSHROOM_BLOCK),
    RED_NETHER_BRICK_SLAB("red_nether_brick_slab", () -> Items.RED_NETHER_BRICK_SLAB),
    RED_NETHER_BRICK_STAIRS("red_nether_brick_stairs", () -> Items.RED_NETHER_BRICK_STAIRS),
    RED_NETHER_BRICK_WALL("red_nether_brick_wall", () -> Items.RED_NETHER_BRICK_WALL),
    RED_NETHER_BRICKS("red_nether_bricks", () -> Items.RED_NETHER_BRICKS), RED_SAND("red_sand", () -> Items.RED_SAND),
    RED_SANDSTONE("red_sandstone", () -> Items.RED_SANDSTONE),
    RED_SANDSTONE_SLAB("red_sandstone_slab", () -> Items.RED_SANDSTONE_SLAB),
    RED_SANDSTONE_STAIRS("red_sandstone_stairs", () -> Items.RED_SANDSTONE_STAIRS),
    RED_SANDSTONE_WALL("red_sandstone_wall", () -> Items.RED_SANDSTONE_WALL),
    RED_SHULKER_BOX("red_shulker_box", () -> Items.RED_SHULKER_BOX),
    RED_STAINED_GLASS("red_stained_glass", () -> Items.RED_STAINED_GLASS),
    RED_STAINED_GLASS_PANE("red_stained_glass_pane", () -> Items.RED_STAINED_GLASS_PANE),
    RED_TERRACOTTA("red_terracotta", () -> Items.RED_TERRACOTTA), RED_TULIP("red_tulip", () -> Items.RED_TULIP),
    RED_WOOL("red_wool", () -> Items.RED_WOOL), REDSTONE("redstone", () -> Items.REDSTONE),
    REDSTONE_BLOCK("redstone_block", () -> Items.REDSTONE_BLOCK),
    REDSTONE_LAMP("redstone_lamp", () -> Items.REDSTONE_LAMP), REDSTONE_ORE("redstone_ore", () -> Items.REDSTONE_ORE),
    REDSTONE_TORCH("redstone_torch", () -> Items.REDSTONE_TORCH), REPEATER("repeater", () -> Items.REPEATER),
    REPEATING_COMMAND_BLOCK("repeating_command_block", () -> Items.REPEATING_COMMAND_BLOCK),
    RESPAWN_ANCHOR("respawn_anchor", () -> Items.RESPAWN_ANCHOR), ROSE_BUSH("rose_bush", () -> Items.ROSE_BUSH),
    ROTTEN_FLESH("rotten_flesh", () -> Items.ROTTEN_FLESH), SADDLE("saddle", () -> Items.SADDLE),
    SALMON("salmon", () -> Items.SALMON), SALMON_BUCKET("salmon_bucket", () -> Items.SALMON_BUCKET),
    SALMON_SPAWN_EGG("salmon_spawn_egg", () -> Items.SALMON_SPAWN_EGG), SAND("sand", () -> Items.SAND),
    SANDSTONE("sandstone", () -> Items.SANDSTONE), SANDSTONE_SLAB("sandstone_slab", () -> Items.SANDSTONE_SLAB),
    SANDSTONE_STAIRS("sandstone_stairs", () -> Items.SANDSTONE_STAIRS),
    SANDSTONE_WALL("sandstone_wall", () -> Items.SANDSTONE_WALL), SCAFFOLDING("scaffolding", () -> Items.SCAFFOLDING),
    SCUTE("scute", () -> Items.SCUTE), SEA_LANTERN("sea_lantern", () -> Items.SEA_LANTERN),
    SEA_PICKLE("sea_pickle", () -> Items.SEA_PICKLE), SEAGRASS("seagrass", () -> Items.SEAGRASS),
    SHEARS("shears", () -> Items.SHEARS), SHEEP_SPAWN_EGG("sheep_spawn_egg", () -> Items.SHEEP_SPAWN_EGG),
    SHIELD("shield", () -> Items.SHIELD), SHROOMLIGHT("shroomlight", () -> Items.SHROOMLIGHT),
    SHULKER_BOX("shulker_box", () -> Items.SHULKER_BOX), SHULKER_SHELL("shulker_shell", () -> Items.SHULKER_SHELL),
    SHULKER_SPAWN_EGG("shulker_spawn_egg", () -> Items.SHULKER_SPAWN_EGG),
    SILVERFISH_SPAWN_EGG("silverfish_spawn_egg", () -> Items.SILVERFISH_SPAWN_EGG),
    SKELETON_HORSE_SPAWN_EGG("skeleton_horse_spawn_egg", () -> Items.SKELETON_HORSE_SPAWN_EGG),
    SKELETON_SKULL("skeleton_skull", () -> Items.SKELETON_SKULL),
    SKELETON_SPAWN_EGG("skeleton_spawn_egg", () -> Items.SKELETON_SPAWN_EGG),
    SKULL_BANNER_PATTERN("skull_banner_pattern", () -> Items.SKULL_BANNER_PATTERN),
    SLIME_BALL("slime_ball", () -> Items.SLIME_BALL), SLIME_BLOCK("slime_block", () -> Items.SLIME_BLOCK),
    SLIME_SPAWN_EGG("slime_spawn_egg", () -> Items.SLIME_SPAWN_EGG),
    SMITHING_TABLE("smithing_table", () -> Items.SMITHING_TABLE), SMOKER("smoker", () -> Items.SMOKER),
    SMOOTH_QUARTZ("smooth_quartz", () -> Items.SMOOTH_QUARTZ),
    SMOOTH_QUARTZ_SLAB("smooth_quartz_slab", () -> Items.SMOOTH_QUARTZ_SLAB),
    SMOOTH_QUARTZ_STAIRS("smooth_quartz_stairs", () -> Items.SMOOTH_QUARTZ_STAIRS),
    SMOOTH_RED_SANDSTONE("smooth_red_sandstone", () -> Items.SMOOTH_RED_SANDSTONE),
    SMOOTH_RED_SANDSTONE_SLAB("smooth_red_sandstone_slab", () -> Items.SMOOTH_RED_SANDSTONE_SLAB),
    SMOOTH_RED_SANDSTONE_STAIRS("smooth_red_sandstone_stairs", () -> Items.SMOOTH_RED_SANDSTONE_STAIRS),
    SMOOTH_SANDSTONE("smooth_sandstone", () -> Items.SMOOTH_SANDSTONE),
    SMOOTH_SANDSTONE_SLAB("smooth_sandstone_slab", () -> Items.SMOOTH_SANDSTONE_SLAB),
    SMOOTH_SANDSTONE_STAIRS("smooth_sandstone_stairs", () -> Items.SMOOTH_SANDSTONE_STAIRS),
    SMOOTH_STONE("smooth_stone", () -> Items.SMOOTH_STONE),
    SMOOTH_STONE_SLAB("smooth_stone_slab", () -> Items.SMOOTH_STONE_SLAB), SNOW("snow", () -> Items.SNOW),
    SNOW_BLOCK("snow_block", () -> Items.SNOW_BLOCK), SNOWBALL("snowball", () -> Items.SNOWBALL),
    SOUL_CAMPFIRE("soul_campfire", () -> Items.SOUL_CAMPFIRE), SOUL_LANTERN("soul_lantern", () -> Items.SOUL_LANTERN),
    SOUL_SAND("soul_sand", () -> Items.SOUL_SAND), SOUL_SOIL("soul_soil", () -> Items.SOUL_SOIL),
    SOUL_TORCH("soul_torch", () -> Items.SOUL_TORCH), SPAWNER("spawner", () -> Items.SPAWNER),
    SPECTRAL_ARROW("spectral_arrow", () -> Items.SPECTRAL_ARROW), SPIDER_EYE("spider_eye", () -> Items.SPIDER_EYE),
    SPIDER_SPAWN_EGG("spider_spawn_egg", () -> Items.SPIDER_SPAWN_EGG),
    SPLASH_POTION("splash_potion", () -> Items.SPLASH_POTION), SPONGE("sponge", () -> Items.SPONGE),
    SPRUCE_BOAT("spruce_boat", () -> Items.SPRUCE_BOAT), SPRUCE_BUTTON("spruce_button", () -> Items.SPRUCE_BUTTON),
    SPRUCE_DOOR("spruce_door", () -> Items.SPRUCE_DOOR), SPRUCE_FENCE("spruce_fence", () -> Items.SPRUCE_FENCE),
    SPRUCE_FENCE_GATE("spruce_fence_gate", () -> Items.SPRUCE_FENCE_GATE),
    SPRUCE_LEAVES("spruce_leaves", () -> Items.SPRUCE_LEAVES), SPRUCE_LOG("spruce_log", () -> Items.SPRUCE_LOG),
    SPRUCE_PLANKS("spruce_planks", () -> Items.SPRUCE_PLANKS),
    SPRUCE_PRESSURE_PLATE("spruce_pressure_plate", () -> Items.SPRUCE_PRESSURE_PLATE),
    SPRUCE_SAPLING("spruce_sapling", () -> Items.SPRUCE_SAPLING), SPRUCE_SIGN("spruce_sign", () -> Items.SPRUCE_SIGN),
    SPRUCE_SLAB("spruce_slab", () -> Items.SPRUCE_SLAB), SPRUCE_STAIRS("spruce_stairs", () -> Items.SPRUCE_STAIRS),
    SPRUCE_TRAPDOOR("spruce_trapdoor", () -> Items.SPRUCE_TRAPDOOR),
    SPRUCE_WOOD("spruce_wood", () -> Items.SPRUCE_WOOD),
    SQUID_SPAWN_EGG("squid_spawn_egg", () -> Items.SQUID_SPAWN_EGG), STICK("stick", () -> Items.STICK),
    STICKY_PISTON("sticky_piston", () -> Items.STICKY_PISTON), STONE("stone", () -> Items.STONE),
    STONE_AXE("stone_axe", () -> Items.STONE_AXE), STONE_BRICK_SLAB("stone_brick_slab", () -> Items.STONE_BRICK_SLAB),
    STONE_BRICK_STAIRS("stone_brick_stairs", () -> Items.STONE_BRICK_STAIRS),
    STONE_BRICK_WALL("stone_brick_wall", () -> Items.STONE_BRICK_WALL),
    STONE_BRICKS("stone_bricks", () -> Items.STONE_BRICKS), STONE_BUTTON("stone_button", () -> Items.STONE_BUTTON),
    STONE_HOE("stone_hoe", () -> Items.STONE_HOE), STONE_PICKAXE("stone_pickaxe", () -> Items.STONE_PICKAXE),
    STONE_PRESSURE_PLATE("stone_pressure_plate", () -> Items.STONE_PRESSURE_PLATE),
    STONE_SHOVEL("stone_shovel", () -> Items.STONE_SHOVEL), STONE_SLAB("stone_slab", () -> Items.STONE_SLAB),
    STONE_STAIRS("stone_stairs", () -> Items.STONE_STAIRS), STONE_SWORD("stone_sword", () -> Items.STONE_SWORD),
    STONECUTTER("stonecutter", () -> Items.STONECUTTER),
    STRAY_SPAWN_EGG("stray_spawn_egg", () -> Items.STRAY_SPAWN_EGG),
    STRIDER_SPAWN_EGG("strider_spawn_egg", () -> Items.STRIDER_SPAWN_EGG), STRING("string", () -> Items.STRING),
    STRIPPED_ACACIA_LOG("stripped_acacia_log", () -> Items.STRIPPED_ACACIA_LOG),
    STRIPPED_ACACIA_WOOD("stripped_acacia_wood", () -> Items.STRIPPED_ACACIA_WOOD),
    STRIPPED_BIRCH_LOG("stripped_birch_log", () -> Items.STRIPPED_BIRCH_LOG),
    STRIPPED_BIRCH_WOOD("stripped_birch_wood", () -> Items.STRIPPED_BIRCH_WOOD),
    STRIPPED_CRIMSON_HYPHAE("stripped_crimson_hyphae", () -> Items.STRIPPED_CRIMSON_HYPHAE),
    STRIPPED_CRIMSON_STEM("stripped_crimson_stem", () -> Items.STRIPPED_CRIMSON_STEM),
    STRIPPED_DARK_OAK_LOG("stripped_dark_oak_log", () -> Items.STRIPPED_DARK_OAK_LOG),
    STRIPPED_DARK_OAK_WOOD("stripped_dark_oak_wood", () -> Items.STRIPPED_DARK_OAK_WOOD),
    STRIPPED_JUNGLE_LOG("stripped_jungle_log", () -> Items.STRIPPED_JUNGLE_LOG),
    STRIPPED_JUNGLE_WOOD("stripped_jungle_wood", () -> Items.STRIPPED_JUNGLE_WOOD),
    STRIPPED_OAK_LOG("stripped_oak_log", () -> Items.STRIPPED_OAK_LOG),
    STRIPPED_OAK_WOOD("stripped_oak_wood", () -> Items.STRIPPED_OAK_WOOD),
    STRIPPED_SPRUCE_LOG("stripped_spruce_log", () -> Items.STRIPPED_SPRUCE_LOG),
    STRIPPED_SPRUCE_WOOD("stripped_spruce_wood", () -> Items.STRIPPED_SPRUCE_WOOD),
    STRIPPED_WARPED_HYPHAE("stripped_warped_hyphae", () -> Items.STRIPPED_WARPED_HYPHAE),
    STRIPPED_WARPED_STEM("stripped_warped_stem", () -> Items.STRIPPED_WARPED_STEM),
    STRUCTURE_BLOCK("structure_block", () -> Items.STRUCTURE_BLOCK),
    STRUCTURE_VOID("structure_void", () -> Items.STRUCTURE_VOID), SUGAR("sugar", () -> Items.SUGAR),
    SUGAR_CANE("sugar_cane", () -> Items.SUGAR_CANE), SUNFLOWER("sunflower", () -> Items.SUNFLOWER),
    SUSPICIOUS_STEW("suspicious_stew", () -> Items.SUSPICIOUS_STEW),
    SWEET_BERRIES("sweet_berries", () -> Items.SWEET_BERRIES), TALL_GRASS("tall_grass", () -> Items.TALL_GRASS),
    TARGET("target", () -> Items.TARGET), TERRACOTTA("terracotta", () -> Items.TERRACOTTA),
    TIPPED_ARROW("tipped_arrow", () -> Items.TIPPED_ARROW), TNT("tnt", () -> Items.TNT),
    TNT_MINECART("tnt_minecart", () -> Items.TNT_MINECART), TORCH("torch", () -> Items.TORCH),
    TOTEM_OF_UNDYING("totem_of_undying", () -> Items.TOTEM_OF_UNDYING),
    TRADER_LLAMA_SPAWN_EGG("trader_llama_spawn_egg", () -> Items.TRADER_LLAMA_SPAWN_EGG),
    TRAPPED_CHEST("trapped_chest", () -> Items.TRAPPED_CHEST), TRIDENT("trident", () -> Items.TRIDENT),
    TRIPWIRE_HOOK("tripwire_hook", () -> Items.TRIPWIRE_HOOK),
    TROPICAL_FISH("tropical_fish", () -> Items.TROPICAL_FISH),
    TROPICAL_FISH_BUCKET("tropical_fish_bucket", () -> Items.TROPICAL_FISH_BUCKET),
    TROPICAL_FISH_SPAWN_EGG("tropical_fish_spawn_egg", () -> Items.TROPICAL_FISH_SPAWN_EGG),
    TUBE_CORAL("tube_coral", () -> Items.TUBE_CORAL),
    TUBE_CORAL_BLOCK("tube_coral_block", () -> Items.TUBE_CORAL_BLOCK),
    TUBE_CORAL_FAN("tube_coral_fan", () -> Items.TUBE_CORAL_FAN), TURTLE_EGG("turtle_egg", () -> Items.TURTLE_EGG),
    TURTLE_HELMET("turtle_helmet", () -> Items.TURTLE_HELMET),
    TURTLE_SPAWN_EGG("turtle_spawn_egg", () -> Items.TURTLE_SPAWN_EGG),
    TWISTING_VINES("twisting_vines", () -> Items.TWISTING_VINES),
    VEX_SPAWN_EGG("vex_spawn_egg", () -> Items.VEX_SPAWN_EGG),
    VILLAGER_SPAWN_EGG("villager_spawn_egg", () -> Items.VILLAGER_SPAWN_EGG),
    VINDICATOR_SPAWN_EGG("vindicator_spawn_egg", () -> Items.VINDICATOR_SPAWN_EGG), VINE("vine", () -> Items.VINE),
    WANDERING_TRADER_SPAWN_EGG("wandering_trader_spawn_egg", () -> Items.WANDERING_TRADER_SPAWN_EGG),
    WARPED_BUTTON("warped_button", () -> Items.WARPED_BUTTON), WARPED_DOOR("warped_door", () -> Items.WARPED_DOOR),
    WARPED_FENCE("warped_fence", () -> Items.WARPED_FENCE),
    WARPED_FENCE_GATE("warped_fence_gate", () -> Items.WARPED_FENCE_GATE),
    WARPED_FUNGUS("warped_fungus", () -> Items.WARPED_FUNGUS),
    WARPED_FUNGUS_ON_A_STICK("warped_fungus_on_a_stick", () -> Items.WARPED_FUNGUS_ON_A_STICK),
    WARPED_HYPHAE("warped_hyphae", () -> Items.WARPED_HYPHAE),
    WARPED_NYLIUM("warped_nylium", () -> Items.WARPED_NYLIUM),
    WARPED_PLANKS("warped_planks", () -> Items.WARPED_PLANKS),
    WARPED_PRESSURE_PLATE("warped_pressure_plate", () -> Items.WARPED_PRESSURE_PLATE),
    WARPED_ROOTS("warped_roots", () -> Items.WARPED_ROOTS), WARPED_SIGN("warped_sign", () -> Items.WARPED_SIGN),
    WARPED_SLAB("warped_slab", () -> Items.WARPED_SLAB), WARPED_STAIRS("warped_stairs", () -> Items.WARPED_STAIRS),
    WARPED_STEM("warped_stem", () -> Items.WARPED_STEM),
    WARPED_TRAPDOOR("warped_trapdoor", () -> Items.WARPED_TRAPDOOR),
    WARPED_WART_BLOCK("warped_wart_block", () -> Items.WARPED_WART_BLOCK),
    WATER_BUCKET("water_bucket", () -> Items.WATER_BUCKET), WEEPING_VINES("weeping_vines", () -> Items.WEEPING_VINES),
    WET_SPONGE("wet_sponge", () -> Items.WET_SPONGE), WHEAT("wheat", () -> Items.WHEAT),
    WHEAT_SEEDS("wheat_seeds", () -> Items.WHEAT_SEEDS), WHITE_BANNER("white_banner", () -> Items.WHITE_BANNER),
    WHITE_BED("white_bed", () -> Items.WHITE_BED), WHITE_CARPET("white_carpet", () -> Items.WHITE_CARPET),
    WHITE_CONCRETE("white_concrete", () -> Items.WHITE_CONCRETE),
    WHITE_CONCRETE_POWDER("white_concrete_powder", () -> Items.WHITE_CONCRETE_POWDER),
    WHITE_DYE("white_dye", () -> Items.WHITE_DYE),
    WHITE_GLAZED_TERRACOTTA("white_glazed_terracotta", () -> Items.WHITE_GLAZED_TERRACOTTA),
    WHITE_SHULKER_BOX("white_shulker_box", () -> Items.WHITE_SHULKER_BOX),
    WHITE_STAINED_GLASS("white_stained_glass", () -> Items.WHITE_STAINED_GLASS),
    WHITE_STAINED_GLASS_PANE("white_stained_glass_pane", () -> Items.WHITE_STAINED_GLASS_PANE),
    WHITE_TERRACOTTA("white_terracotta", () -> Items.WHITE_TERRACOTTA),
    WHITE_TULIP("white_tulip", () -> Items.WHITE_TULIP), WHITE_WOOL("white_wool", () -> Items.WHITE_WOOL),
    WITCH_SPAWN_EGG("witch_spawn_egg", () -> Items.WITCH_SPAWN_EGG),
    WITHER_ROSE("wither_rose", () -> Items.WITHER_ROSE),
    WITHER_SKELETON_SKULL("wither_skeleton_skull", () -> Items.WITHER_SKELETON_SKULL),
    WITHER_SKELETON_SPAWN_EGG("wither_skeleton_spawn_egg", () -> Items.WITHER_SKELETON_SPAWN_EGG),
    WOLF_SPAWN_EGG("wolf_spawn_egg", () -> Items.WOLF_SPAWN_EGG), WOODEN_AXE("wooden_axe", () -> Items.WOODEN_AXE),
    WOODEN_HOE("wooden_hoe", () -> Items.WOODEN_HOE), WOODEN_PICKAXE("wooden_pickaxe", () -> Items.WOODEN_PICKAXE),
    WOODEN_SHOVEL("wooden_shovel", () -> Items.WOODEN_SHOVEL), WOODEN_SWORD("wooden_sword", () -> Items.WOODEN_SWORD),
    WRITABLE_BOOK("writable_book", () -> Items.WRITABLE_BOOK), WRITTEN_BOOK("written_book", () -> Items.WRITTEN_BOOK),
    YELLOW_BANNER("yellow_banner", () -> Items.YELLOW_BANNER), YELLOW_BED("yellow_bed", () -> Items.YELLOW_BED),
    YELLOW_CARPET("yellow_carpet", () -> Items.YELLOW_CARPET),
    YELLOW_CONCRETE("yellow_concrete", () -> Items.YELLOW_CONCRETE),
    YELLOW_CONCRETE_POWDER("yellow_concrete_powder", () -> Items.YELLOW_CONCRETE_POWDER),
    YELLOW_DYE("yellow_dye", () -> Items.YELLOW_DYE),
    YELLOW_GLAZED_TERRACOTTA("yellow_glazed_terracotta", () -> Items.YELLOW_GLAZED_TERRACOTTA),
    YELLOW_SHULKER_BOX("yellow_shulker_box", () -> Items.YELLOW_SHULKER_BOX),
    YELLOW_STAINED_GLASS("yellow_stained_glass", () -> Items.YELLOW_STAINED_GLASS),
    YELLOW_STAINED_GLASS_PANE("yellow_stained_glass_pane", () -> Items.YELLOW_STAINED_GLASS_PANE),
    YELLOW_TERRACOTTA("yellow_terracotta", () -> Items.YELLOW_TERRACOTTA),
    YELLOW_WOOL("yellow_wool", () -> Items.YELLOW_WOOL),
    ZOGLIN_SPAWN_EGG("zoglin_spawn_egg", () -> Items.ZOGLIN_SPAWN_EGG),
    ZOMBIE_HEAD("zombie_head", () -> Items.ZOMBIE_HEAD),
    ZOMBIE_HORSE_SPAWN_EGG("zombie_horse_spawn_egg", () -> Items.ZOMBIE_HORSE_SPAWN_EGG),
    ZOMBIE_SPAWN_EGG("zombie_spawn_egg", () -> Items.ZOMBIE_SPAWN_EGG),
    ZOMBIE_VILLAGER_SPAWN_EGG("zombie_villager_spawn_egg", () -> Items.ZOMBIE_VILLAGER_SPAWN_EGG),
    ZOMBIFIED_PIGLIN_SPAWN_EGG("zombified_piglin_spawn_egg", () -> Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);

    /**
     * This class contains a reference to all of the simple {@link VanillaItem}s.
     * These are items that can be extended using the {@link ItemExtender} class.
     * 
     * @author Joseph Collard <jcollard@worldsofminecraft.com>
     *
     */
    public static class Simple {
        public static VanillaItem APPLE = VanillaItem.APPLE;
        public static VanillaItem ARMOR_STAND = VanillaItem.ARMOR_STAND;
        public static VanillaItem ARROW = VanillaItem.ARROW;
        public static VanillaItem BAKED_POTATO = VanillaItem.BAKED_POTATO;
        public static VanillaItem BEEF = VanillaItem.BEEF;
        public static VanillaItem BEETROOT = VanillaItem.BEETROOT;
        public static VanillaItem BEETROOT_SOUP = VanillaItem.BEETROOT_SOUP;
        public static VanillaItem BLAZE_POWDER = VanillaItem.BLAZE_POWDER;
        public static VanillaItem BLAZE_ROD = VanillaItem.BLAZE_ROD;
        public static VanillaItem BONE = VanillaItem.BONE;
        public static VanillaItem BONE_MEAL = VanillaItem.BONE_MEAL;
        public static VanillaItem BOOK = VanillaItem.BOOK;
        public static VanillaItem BOW = VanillaItem.BOW;
        public static VanillaItem BOWL = VanillaItem.BOWL;
        public static VanillaItem BREAD = VanillaItem.BREAD;
        public static VanillaItem BRICK = VanillaItem.BRICK;
        public static VanillaItem CHARCOAL = VanillaItem.CHARCOAL;
        public static VanillaItem CHICKEN = VanillaItem.CHICKEN;
        public static VanillaItem CHORUS_FRUIT = VanillaItem.CHORUS_FRUIT;
        public static VanillaItem CLAY_BALL = VanillaItem.CLAY_BALL;
        public static VanillaItem CLOCK = VanillaItem.CLOCK;
        public static VanillaItem COAL = VanillaItem.COAL;
        public static VanillaItem COD = VanillaItem.COD;
        public static VanillaItem COMPASS = VanillaItem.COMPASS;
        public static VanillaItem COOKED_BEEF = VanillaItem.COOKED_BEEF;
        public static VanillaItem COOKED_CHICKEN = VanillaItem.COOKED_CHICKEN;
        public static VanillaItem COOKED_COD = VanillaItem.COOKED_COD;
        public static VanillaItem COOKED_MUTTON = VanillaItem.COOKED_MUTTON;
        public static VanillaItem COOKED_PORKCHOP = VanillaItem.COOKED_PORKCHOP;
        public static VanillaItem COOKED_RABBIT = VanillaItem.COOKED_RABBIT;
        public static VanillaItem COOKED_SALMON = VanillaItem.COOKED_SALMON;
        public static VanillaItem COOKIE = VanillaItem.COOKIE;
        public static VanillaItem CROSSBOW = VanillaItem.CROSSBOW;
        public static VanillaItem DEBUG_STICK = VanillaItem.DEBUG_STICK;
        public static VanillaItem DIAMOND = VanillaItem.DIAMOND;
        public static VanillaItem DRAGON_BREATH = VanillaItem.DRAGON_BREATH;
        public static VanillaItem DRIED_KELP = VanillaItem.DRIED_KELP;
        public static VanillaItem EGG = VanillaItem.EGG;
        public static VanillaItem ELYTRA = VanillaItem.ELYTRA;
        public static VanillaItem EMERALD = VanillaItem.EMERALD;
        public static VanillaItem ENCHANTED_BOOK = VanillaItem.ENCHANTED_BOOK;
        public static VanillaItem ENCHANTED_GOLDEN_APPLE = VanillaItem.ENCHANTED_GOLDEN_APPLE;
        public static VanillaItem END_CRYSTAL = VanillaItem.END_CRYSTAL;
        public static VanillaItem ENDER_EYE = VanillaItem.ENDER_EYE;
        public static VanillaItem ENDER_PEARL = VanillaItem.ENDER_PEARL;
        public static VanillaItem EXPERIENCE_BOTTLE = VanillaItem.EXPERIENCE_BOTTLE;
        public static VanillaItem FEATHER = VanillaItem.FEATHER;
        public static VanillaItem FERMENTED_SPIDER_EYE = VanillaItem.FERMENTED_SPIDER_EYE;
        public static VanillaItem FILLED_MAP = VanillaItem.FILLED_MAP;
        public static VanillaItem FIRE_CHARGE = VanillaItem.FIRE_CHARGE;
        public static VanillaItem FIREWORK_ROCKET = VanillaItem.FIREWORK_ROCKET;
        public static VanillaItem FIREWORK_STAR = VanillaItem.FIREWORK_STAR;
        public static VanillaItem FISHING_ROD = VanillaItem.FISHING_ROD;
        public static VanillaItem FLINT = VanillaItem.FLINT;
        public static VanillaItem FLINT_AND_STEEL = VanillaItem.FLINT_AND_STEEL;
        public static VanillaItem GHAST_TEAR = VanillaItem.GHAST_TEAR;
        public static VanillaItem GLASS_BOTTLE = VanillaItem.GLASS_BOTTLE;
        public static VanillaItem GLISTERING_MELON_SLICE = VanillaItem.GLISTERING_MELON_SLICE;
        public static VanillaItem GLOWSTONE_DUST = VanillaItem.GLOWSTONE_DUST;
        public static VanillaItem GOLD_INGOT = VanillaItem.GOLD_INGOT;
        public static VanillaItem GOLD_NUGGET = VanillaItem.GOLD_NUGGET;
        public static VanillaItem GOLDEN_APPLE = VanillaItem.GOLDEN_APPLE;
        public static VanillaItem GOLDEN_CARROT = VanillaItem.GOLDEN_CARROT;
        public static VanillaItem GUNPOWDER = VanillaItem.GUNPOWDER;
        public static VanillaItem HEART_OF_THE_SEA = VanillaItem.HEART_OF_THE_SEA;
        public static VanillaItem HONEY_BOTTLE = VanillaItem.HONEY_BOTTLE;
        public static VanillaItem HONEYCOMB = VanillaItem.HONEYCOMB;
        public static VanillaItem INK_SAC = VanillaItem.INK_SAC;
        public static VanillaItem IRON_INGOT = VanillaItem.IRON_INGOT;
        public static VanillaItem IRON_NUGGET = VanillaItem.IRON_NUGGET;
        public static VanillaItem ITEM_FRAME = VanillaItem.ITEM_FRAME;
        public static VanillaItem KNOWLEDGE_BOOK = VanillaItem.KNOWLEDGE_BOOK;
        public static VanillaItem LAPIS_LAZULI = VanillaItem.LAPIS_LAZULI;
        public static VanillaItem LEAD = VanillaItem.LEAD;
        public static VanillaItem LEATHER = VanillaItem.LEATHER;
        public static VanillaItem LINGERING_POTION = VanillaItem.LINGERING_POTION;
        public static VanillaItem MAGMA_CREAM = VanillaItem.MAGMA_CREAM;
        public static VanillaItem MAP = VanillaItem.MAP;
        public static VanillaItem MELON_SLICE = VanillaItem.MELON_SLICE;
        public static VanillaItem MILK_BUCKET = VanillaItem.MILK_BUCKET;
        public static VanillaItem MUSHROOM_STEW = VanillaItem.MUSHROOM_STEW;
        public static VanillaItem MUTTON = VanillaItem.MUTTON;
        public static VanillaItem NAME_TAG = VanillaItem.NAME_TAG;
        public static VanillaItem NAUTILUS_SHELL = VanillaItem.NAUTILUS_SHELL;
        public static VanillaItem NETHER_BRICK = VanillaItem.NETHER_BRICK;
        public static VanillaItem NETHER_STAR = VanillaItem.NETHER_STAR;
        public static VanillaItem NETHERITE_INGOT = VanillaItem.NETHERITE_INGOT;
        public static VanillaItem NETHERITE_SCRAP = VanillaItem.NETHERITE_SCRAP;
        public static VanillaItem PAPER = VanillaItem.PAPER;
        public static VanillaItem PHANTOM_MEMBRANE = VanillaItem.PHANTOM_MEMBRANE;
        public static VanillaItem POISONOUS_POTATO = VanillaItem.POISONOUS_POTATO;
        public static VanillaItem POPPED_CHORUS_FRUIT = VanillaItem.POPPED_CHORUS_FRUIT;
        public static VanillaItem PORKCHOP = VanillaItem.PORKCHOP;
        public static VanillaItem POTION = VanillaItem.POTION;
        public static VanillaItem PRISMARINE_CRYSTALS = VanillaItem.PRISMARINE_CRYSTALS;
        public static VanillaItem PRISMARINE_SHARD = VanillaItem.PRISMARINE_SHARD;
        public static VanillaItem PUFFERFISH = VanillaItem.PUFFERFISH;
        public static VanillaItem PUMPKIN_PIE = VanillaItem.PUMPKIN_PIE;
        public static VanillaItem QUARTZ = VanillaItem.QUARTZ;
        public static VanillaItem RABBIT = VanillaItem.RABBIT;
        public static VanillaItem RABBIT_FOOT = VanillaItem.RABBIT_FOOT;
        public static VanillaItem RABBIT_HIDE = VanillaItem.RABBIT_HIDE;
        public static VanillaItem RABBIT_STEW = VanillaItem.RABBIT_STEW;
        public static VanillaItem ROTTEN_FLESH = VanillaItem.ROTTEN_FLESH;
        public static VanillaItem SADDLE = VanillaItem.SADDLE;
        public static VanillaItem SALMON = VanillaItem.SALMON;
        public static VanillaItem SCUTE = VanillaItem.SCUTE;
        public static VanillaItem SHEARS = VanillaItem.SHEARS;
        public static VanillaItem SHIELD = VanillaItem.SHIELD;
        public static VanillaItem SHULKER_SHELL = VanillaItem.SHULKER_SHELL;
        public static VanillaItem SLIME_BALL = VanillaItem.SLIME_BALL;
        public static VanillaItem SNOWBALL = VanillaItem.SNOWBALL;
        public static VanillaItem SPECTRAL_ARROW = VanillaItem.SPECTRAL_ARROW;
        public static VanillaItem SPIDER_EYE = VanillaItem.SPIDER_EYE;
        public static VanillaItem SPLASH_POTION = VanillaItem.SPLASH_POTION;
        public static VanillaItem STICK = VanillaItem.STICK;
        public static VanillaItem SUGAR = VanillaItem.SUGAR;
        public static VanillaItem SUSPICIOUS_STEW = VanillaItem.SUSPICIOUS_STEW;
        public static VanillaItem TIPPED_ARROW = VanillaItem.TIPPED_ARROW;
        public static VanillaItem TOTEM_OF_UNDYING = VanillaItem.TOTEM_OF_UNDYING;
        public static VanillaItem TRIDENT = VanillaItem.TRIDENT;
        public static VanillaItem TROPICAL_FISH = VanillaItem.TROPICAL_FISH;
        public static VanillaItem WHEAT = VanillaItem.WHEAT;
        public static VanillaItem WRITABLE_BOOK = VanillaItem.WRITABLE_BOOK;
        public static VanillaItem WRITTEN_BOOK = VanillaItem.WRITTEN_BOOK;
    }

    /** This {@link VanillaItem}s registry name */
    public final String REGISTRY_NAME;

    /** This {@link VanillaItem}s recipe name (e.g. "minecraft:apple") */
    public final String RECIPE_NAME;

    /**
     * This {@link VanillaItem}s supplier. Using this supplier outside of a running
     * game may result in errors.
     */
    @Volatile
    public final Supplier<Item> SUPPLIER;

    private static final List<VanillaItem> items = new ArrayList<>(values().length);

    static {
        for (VanillaItem i : values()) {
            items.add(i);
        }
    }

    private VanillaItem(String registryName, Supplier<Item> itemSupplier) {
        this.REGISTRY_NAME = "item/" + registryName;
        this.RECIPE_NAME = "minecraft:" + registryName;
        this.SUPPLIER = itemSupplier;
    }

    public static List<VanillaItem> valuesList() {
        return Collections.unmodifiableList(items);
    }

}
