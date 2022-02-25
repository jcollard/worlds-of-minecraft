package com.worldsofminecraft.mod.util.math;

public class Vector3d {

    public final double x, y, z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3d convert(net.minecraft.util.math.vector.Vector3d v) {
        return new Vector3d(v.x, v.y, v.z);
    }

}
