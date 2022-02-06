package org.chadwickschool.examples.weapons;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.IItem.Tier;
import com.worldsofminecraft.mod.item.QuickSword;
import com.worldsofminecraft.mod.item.util.functional.ItemHitContext;

/**
 * The Fiery Axe is a powerful QuickSword which causes enemy to burst into
 * flames!
 * 
 * @author Joseph Collard <jcollard@worldsofminecraft.com>
 *
 */
//@Mod(FieryAxeMod.MODID)
public class FieryAxeMod extends QuickMod {

    public static final String MODID = "fiery_axe";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Fiery Axe";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);
        builder.logoFile("assets/common/fiery_axe.png");
        builder.description("This mod demonstrates how to add a weapon using QuickSword");

        String name = "Fiery Axe";
        String texture = "assets/common/fiery_axe.png";

        // This specifies the type for this weapon to mimic. This determines
        // the base speed, durability, damage, enchantability, and repair item.
        // Built in Tiers are: Tier.STONE, Tier.IRON, Tier.DIAMOND, Tier.GOLD,
        // and Tier.NETHERITE
        Tier tier = Tier.DIAMOND;
        QuickSword fieryAxe = new QuickSword(name, texture, tier);

        // A normal sword has a damage of 3, this makes the axe do 66% more damage!
        fieryAxe.setDamage(5);
        builder.addItem(fieryAxe);

        fieryAxe.onHit = FieryAxeMod::onHit;

        return builder;
    }

    private static void onHit(ItemHitContext context) {
        // Sets the defender on fire for 5 seconds.
        context.defender.setFire(5);
    }

}
