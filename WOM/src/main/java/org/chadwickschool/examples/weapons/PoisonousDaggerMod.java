package org.chadwickschool.examples.weapons;

import java.util.Random;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.stack.IItemStack;
import com.worldsofminecraft.mod.item.util.functional.ItemHitContext;
import com.worldsofminecraft.mod.item.util.functional.ItemUseContext;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.potion.Effect.Type;

import net.minecraftforge.fml.common.Mod;

/**
 * The Poisonous Dagger is a QuickSword which doesn't do much damage but is
 * incredibly quick and has a 20% chance of causing a deadly poisonous effect to
 * be applied.
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
@Mod(PoisonousDaggerMod.MODID)
public class PoisonousDaggerMod extends QuickMod {

    public static final String MODID = "poisonous_dagger";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Poisonous Dagger";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
        builder.logoFile("assets/common/poisonous_dagger.png");
        builder.description("This mod demonstrates how to add a weapon using QuickSword");

        String name = "Poisonous Dagger";
        String texture = "assets/common/poisonous_dagger.png";

        // This specifies the type for this weapon to mimic. This determines
        // the base speed, durability, damage, enchantability, and repair item.
        // Built in Tiers are: Tier.STONE, Tier.IRON, Tier.DIAMOND, Tier.GOLD,
        // and Tier.NETHERITE
        Tier tier = Tier.IRON;
        QuickSword poisonousDagger = new QuickSword(name, texture, tier);

        // Set the speed of the dagger to be very quick!
        // A netherite sword has a speed of 12. This is twice as fast.
        poisonousDagger.setSpeed(24f);
        builder.addItem(poisonousDagger);

        poisonousDagger.onHit = PoisonousDaggerMod::onHit;

        return builder;
    }

    private static void onHit(ItemHitContext context) {

        // Randomly generate a number between 0 and 1
        Random random = new Random();
        double chanceToPoison = random.nextDouble();

        // 20% chance to poison
        if (chanceToPoison < 0.20) {

            // Create a level 5 poison effect
            Effect poison = new Effect(Type.POISON);
            poison.level(5)
                  .seconds(10);

            // Add the effect to the defender
            context.defender.addEffect(poison);

            // Display a message to the attacker letting them know
            // the defender was poisoned
            String name = context.defender.getName();
            String message = "The " + name + " looks sick.";
            context.attacker.showMessage(message);
        }

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

    public static void main(String... args) {
        new PoisonousDaggerMod().getBuilder()
                                .build();
    }

}
