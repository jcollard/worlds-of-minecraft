package com.worldsofminecraft.mod.item;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.worldsofminecraft.mod.util.Utils;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public abstract class AbstractItem implements IItem {

	/**
	 * All items that have been constructed normally. They are added during
	 * construction
	 */
	private static final Set<AbstractItem> ALL_ITEMS = new HashSet<>();

	/** The english translation name */
	private final String name;
	/** The items model */
	private final IItemModel model;
	/** The items properties */
	private final IItem.Properties properties = new IItem.Properties();
	/** The simple registry name for this item */
	private final String simpleRegistryName;
	/** The registry object for this item */
	private RegistryObject<Item> registryObject;
	/** The {@link Action} the player takes when using this item. */
	private Action action = Action.NONE;
	/** The useDuration */
	private int useDuration = 20;
	/** Tracks if this item has been registered on a builder */
	private boolean isRegistered = false;

	/**
	 * Checks all known constructed {@link AbstractItem}s to see if they have been
	 * registered on a builder. This method generates output on the WARN channel of
	 * the {@link Utils#getInstance()} describing which items are were constructed
	 * but not added to a builder prior to calling this method.
	 * 
	 * 
	 * @return false if any items were not registered otherwise true.
	 */
	public static boolean checkRegistration() {
		boolean pass = true;

		for (AbstractItem item : ALL_ITEMS) {
			if (!item.isRegistered) {
				Utils.getInstance().getLogger().warn("WARNING: \"" + item.name
						+ "\" was created but never registered! Did you forget to add it to your mod?");
				pass = false;
			}
		}

		return pass;
	}

	/**
	 * Constructs an AbstractItem specifying its English name and a path to a png
	 * texture to use when rendering in game.
	 * 
	 * @param name
	 * @param texture
	 */
	public AbstractItem(@Nonnull String name, @Nonnull String texture) {
		this(name, PNGResource.get(texture));
	}

	/**
	 * Constructs an AbstractItem specifying its English name and the
	 * {@link IPNGResource} to use as the texture in game
	 * 
	 * @param name
	 * @param texture
	 */
	public AbstractItem(@Nonnull String name, @Nonnull IPNGResource texture) {
		this(name, ItemTexture.get(texture));
	}

	/**
	 * Construct an AbstractItem specifying its English name and the
	 * {@link ItemTexture} to use as the texture in game
	 * 
	 * @param name
	 * @param texture
	 */
	public AbstractItem(@Nonnull String name, @Nonnull ItemTexture texture) {
		this(name, ItemModel.getBuilder(texture).build());
	}

	/**
	 * Construct an AbstractItem specifying its English name and the
	 * {@link IItemModel} to use when rendering in the game.
	 * 
	 * @param name
	 * @param model
	 */
	public AbstractItem(@Nonnull String name, @Nonnull IItemModel model) {
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(model != null, "model must not be null");
		this.name = Utils.getInstance().validateName(name);
		this.simpleRegistryName = Utils.getInstance().validateRegistryName(Utils.getInstance().safeRegistryName(name));
		this.model = model;
		ALL_ITEMS.add(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IItemModel getItemModel() {
		return this.model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSimpleRegistryName() {
		return this.simpleRegistryName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegistryObject<Item> register(DeferredRegister<Item> register) {
		Preconditions.checkState(this.registryObject == null,
				"This item was previously registered. Cannot register an item multiple times.");
		this.registryObject = register.register(simpleRegistryName, this.getItemSupplier());
		return this.registryObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegistryObject<Item> getRegistryObject() {
		Preconditions.checkState(this.registryObject != null, "This item has not yet been registered.");
		return this.registryObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IItem.Properties getProperties() {
		return this.properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getUseDuration() {
		return useDuration;
	}

	/**
	 * Specifies the number of ticks that it takes to use this item. A typical game
	 * runs 20 ticks per second. However, this value can be changed in game.
	 * 
	 * @param duration The number of ticks it takes to use this item
	 */
	public void setUseDuration(int duration) {
		Preconditions.checkArgument(duration >= 0, "Delay must be greater than or equal to 0.");
		this.useDuration = duration;
	}

	/**
	 * Returns the {@link Action} the player takes while using this item.
	 */
	@Override
	public Action getUseAction() {
		return this.action;
	}

	/**
	 * Sets the type of {@link Action} that occurs when this item is used. For
	 * example, if you want to make a shield block, you must specify the
	 * {@link Action#BLOCK} action.
	 * 
	 * @param action the action the player takes while using this item
	 */
	public void setUseAction(@Nonnull Action action) {
		Preconditions.checkArgument(action != null, "Cannot set a null action.");
		this.action = action;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void register() {
		this.isRegistered = true;
	}

	/**
	 * This method provides a supplier to the underlying {@link Item} that will be
	 * used in Minecraft.
	 * 
	 * @return a supplier for the concrete Item to be used.
	 */
	protected abstract Supplier<Item> getItemSupplier();

}
