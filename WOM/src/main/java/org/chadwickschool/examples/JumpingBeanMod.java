package org.chadwickschool.examples;

import com.worldsofminecraft.mod.MinecraftMod;
import com.worldsofminecraft.mod.MinecraftMod.Builder;
import com.worldsofminecraft.mod.QuickMod;
import com.worldsofminecraft.mod.item.QuickFood;
import com.worldsofminecraft.mod.item.tab.ItemTab;
import com.worldsofminecraft.mod.potion.Effect;
import com.worldsofminecraft.mod.potion.Effect.Type;

//TODO: Uncomment this line if you'd like to use this mod
//@Mod(JumpingBeanMod.MODID)
public class JumpingBeanMod extends QuickMod {

    public static final String MODID = "jumping_beans";

    @Override
    public Builder getBuilder() {
        String authors = "Joseph Collard";
        String modName = "Jumping Beans";
        MinecraftMod.Builder builder = MinecraftMod.getBuilder(authors, modName, MODID);

        // Sets the logo that appears on the Mods page in Minecraft
        builder.logoFile("assets/common/jumping_beans.png");
        // Sets the description that appears on this mod in the Mods page
        builder.description("This mod adds a magic bean that lets the player jump really high!");

        // Create a QuickFood specifying its in game name and the texture to use
        QuickFood jumpingBean = new QuickFood("Jumping Beans", "assets/common/jumping_beans.png");

        // Add jumpingBean to the Food tab
        jumpingBean.getProperties()
                   .tab(ItemTab.FOOD);

        // Specify how many food points the jumping bean should restore
        jumpingBean.foodPoints(2);

        // Create an Effect that will be applied after the beans are consumped
        Effect jumpingBeanEffect = new Effect(Type.JUMP_BOOST).level(2)
                                                              .seconds(20);

        // Add the Effect to the Jumping Bean
        jumpingBean.addEffect(jumpingBeanEffect);

        // Add the jumping bean to the builder
        builder.addItem(jumpingBean);

        return builder;
    }

}
