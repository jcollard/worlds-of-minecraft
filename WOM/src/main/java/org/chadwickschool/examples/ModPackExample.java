package org.chadwickschool.examples;

import com.worldsofminecraft.mod.ModPack;

public class ModPackExample {

    public static void main(String[] args) {
        // A ModPack allows you to put multiple mods into the same build.
        // Use the ModPack constructor to create a new one.
        ModPack pack = new ModPack();

        // Next, you construct and add each mod you would like to include
        // pack.addMod(new BananaMod());
        pack.addMod(new JumpingBeanMod());

        // Finally, you call the build() method for the ModPack
        // This will build all of the added mods.
        pack.build();
    }

}
