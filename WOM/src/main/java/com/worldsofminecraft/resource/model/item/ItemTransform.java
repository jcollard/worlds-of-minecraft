package com.worldsofminecraft.resource.model.item;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

public class ItemTransform implements IItemTransform {

    public static class Builder {
        private Vector3D rotation = new Vector3D(0, 0, 0);
        private Vector3D translation = new Vector3D(0, 0, 0);
        private Vector3D scale = new Vector3D(1, 1, 1);

        public Builder rotation(@Nonnull Vector3D rotation) {
            Preconditions.checkArgument(rotation != null);
            this.rotation = rotation;
            return this;
        }

        public Builder rotation(float x, float y, float z) {
            return rotation(new Vector3D(x, y, z));
        }

        public Builder translation(@Nonnull Vector3D translation) {
            Preconditions.checkArgument(translation != null);
            this.translation = translation;
            return this;
        }

        public Builder translation(float x, float y, float z) {
            return translation(new Vector3D(x, y, z));
        }

        public Builder scale(@Nonnull Vector3D scale) {
            Preconditions.checkArgument(scale != null);
            this.scale = scale;
            return this;
        }

        public Builder scale(float x, float y, float z) {
            return scale(new Vector3D(x, y, z));
        }

        public ItemTransform build() {
            return new ItemTransform(this);
        }

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    private final Vector3D rotation;
    private final Vector3D translation;
    private final Vector3D scale;

    private ItemTransform(Builder b) {
        this.rotation = b.rotation;
        this.translation = b.translation;
        this.scale = b.scale;
    }

    public ItemTransform(@Nonnull Vector3D rotation, @Nonnull Vector3D translation, @Nonnull Vector3D scale) {
        Preconditions.checkArgument(rotation != null, "rotation must be non-null.");
        Preconditions.checkArgument(translation != null, "translation must be non-null.");
        Preconditions.checkArgument(scale != null, "scale must be non-null.");
        this.rotation = rotation;
        this.translation = translation;
        this.scale = scale;
    }

    @Override
    public Vector3D getRotation() {
        return rotation;
    }

    @Override
    public Vector3D getTranslation() {
        return translation;
    }

    @Override
    public Vector3D getScale() {
        return scale;
    }

}
