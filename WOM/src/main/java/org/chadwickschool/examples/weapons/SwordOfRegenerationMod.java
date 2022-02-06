package org.chadwickschool.examples.weapons;

import java.util.Random;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.util.functional.ItemUseContext;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.potion.Effect.Type;

/**
 * The Sword of Regeneration is a QuickSword which can bestow upon its user a
 * level 2 regeneration effect. However, using the Sword of Regeneration has a
 * 5% chance to cause it to break!
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
//@Mod(SwordOfRegenerationMod.MODID)
public class SwordOfRegenerationMod extends QuickMod {

    public static final String MODID = "sword_of_regeneration";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Sword of Regeneration";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
        builder.logoFile("assets/common/sword_of_regeneration.png");
        builder.description("This mod demonstrates how to add a custom weapon to a mod");

        String name = "Sword of Regeneration";
        String texture = "assets/common/sword_of_regeneration.png";

        // This specifies the type for this weapon to mimic. This determines
        // the base speed, durability, damage, enchantability, and repair item.
        // Built in Tiers are: Tier.STONE, Tier.IRON, Tier.DIAMOND, Tier.GOLD,
        // and Tier.NETHERITE
        Tier tier = Tier.DIAMOND;
        QuickSword swordOfRegeneration = new QuickSword(name, texture, tier);
        builder.addItem(swordOfRegeneration);

        // Specify the number of ticks it takes to use the item when
        // the player right clicks while wielding this item. By default,
        // 20 ticks is 1 second (but you can change this in game).
        swordOfRegeneration.setUseDuration(20);

        // Runs the code in the onUse method when the player right clicks
        // the item for the specified number of ticks
        swordOfRegeneration.onUse = SwordOfRegenerationMod::onUse;

        return builder;
    }

    public static IItemStack onUse(ItemUseContext context) {
        // Create a regeneration effect to add to the player
        Effect regeneration = new Effect(Type.REGENERATION);
        regeneration.level(2)
                    .seconds(60);

        // Display a message to the player
        context.entity.showMessage("Your wounds begin to heal.");

        // Add the effect to the player
        context.entity.addEffect(regeneration);

        // Randomly generate a number between 0 and 1
        Random random = new Random();
        double chanceToBreak = random.nextDouble();

        if (chanceToBreak < 0.05) {
            // 5% chance that the sword will break!
            context.entity.showMessage("Your sword broke!");

            // Reduce the item stack to 0 (destroys the item)
            context.itemStack.setCount(0);
        }

        // Return the item stack
        return context.itemStack;
    }
}
