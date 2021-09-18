package com.worldsofminecraft.resource.model.item;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

public class ItemDisplay implements IItemDisplay {

    public static class Builder {
        private final Map<Position, IItemTransform> displays = new HashMap<>();

        public Builder transform(@Nonnull Position p, @Nonnull IItemTransform transform) {
            Preconditions.checkArgument(p != null);
            Preconditions.checkArgument(transform != null);
            displays.put(p, transform);
            return this;
        }

        public IItemDisplay build() {
            ItemDisplay d = new ItemDisplay();
            d.displays.putAll(displays);
            return d;
        }

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static final ItemDisplay DEFAULT = new ItemDisplay();

    private final Map<Position, IItemTransform> displays = new HashMap<>();

    private ItemDisplay() {

    }

    @Override
    public Map<Position, IItemTransform> getTransforms() {
        return displays;
    }

}
