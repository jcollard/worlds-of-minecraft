package com.worldsofminecraft.mod.item;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import com.worldsofminecraft.resource.model.item.IItemModel;
import com.worldsofminecraft.resource.model.item.ItemModel;
import com.worldsofminecraft.resource.png.IPNGResource;
import com.worldsofminecraft.resource.png.PNGResource;
import com.worldsofminecraft.resource.texture.item.ItemTexture;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ItemExtender extends AbstractItem {

	private Item delegate;
	private final Supplier<Item> supplier;
	private final Supplier<Item> itemToModel;
	
//	public ItemExtender(@Nonnull String name, @Nonnull String texture, @Nonnull Supplier<Item> itemToModel, Supplier<Item> supplier) {
//		this(name, PNGResource.get(texture), itemToModel, supplier);
//	}
//	
//	public ItemExtender(@Nonnull String name, @Nonnull IPNGResource texture, @Nonnull Supplier<Item> itemToModel, Supplier<Item> supplier) {
//		this(name, ItemTexture.get(texture), itemToModel, supplier);
//	}
//	
//	public ItemExtender(@Nonnull String name, @Nonnull ItemTexture texture, @Nonnull Supplier<Item> itemToModel, Supplier<Item> supplier) {
//		this(name, ItemModel.getBuilder(texture).build(), itemToModel, supplier);
//	}

	public ItemExtender(@Nonnull String name, @Nonnull IItemModel model, @Nonnull Supplier<Item> itemToModel, Supplier<Item> supplier) {
		super(name, model);
		Preconditions.checkArgument(name != null, "item name must not be null.");
		Preconditions.checkArgument(model != null, "model must not be null");
		Preconditions.checkArgument(itemToModel != null, "The itemToModel must be non-null");
		this.supplier = supplier;
		this.itemToModel = itemToModel;
	}

	@Override
	protected Supplier<Item> getItemSupplier() {
		return supplier;
	}
	
	public Item getItemToModel() {
		return this.itemToModel.get();
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public Item getItem() {
		return delegate.getItem();
	}

	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		return delegate.getAttributeModifiers(slot, stack);
	}

	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		return delegate.onDroppedByPlayer(item, player);
	}

	public ITextComponent getHighlightTip(ItemStack item, ITextComponent displayName) {
		return delegate.getHighlightTip(item, displayName);
	}

	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public void onUseTick(World p_219972_1_, LivingEntity p_219972_2_, ItemStack p_219972_3_, int p_219972_4_) {
		delegate.onUseTick(p_219972_1_, p_219972_2_, p_219972_3_, p_219972_4_);
	}

	public boolean verifyTagAfterLoad(CompoundNBT p_179215_1_) {
		return delegate.verifyTagAfterLoad(p_179215_1_);
	}

	public boolean canAttackBlock(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_,
			PlayerEntity p_195938_4_) {
		return delegate.canAttackBlock(p_195938_1_, p_195938_2_, p_195938_3_, p_195938_4_);
	}

	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		return delegate.onItemUseFirst(stack, context);
	}

	public Item asItem() {
		return delegate.asItem();
	}

	public ActionResultType useOn(ItemUseContext p_195939_1_) {
		return delegate.useOn(p_195939_1_);
	}

	public float getDestroySpeed(ItemStack p_150893_1_, BlockState p_150893_2_) {
		return delegate.getDestroySpeed(p_150893_1_, p_150893_2_);
	}

	public boolean isPiglinCurrency(ItemStack stack) {
		return delegate.isPiglinCurrency(stack);
	}

	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
		return delegate.use(p_77659_1_, p_77659_2_, p_77659_3_);
	}

	public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
		return delegate.makesPiglinsNeutral(stack, wearer);
	}

	public ItemStack finishUsingItem(ItemStack p_77654_1_, World p_77654_2_, LivingEntity p_77654_3_) {
		return delegate.finishUsingItem(p_77654_1_, p_77654_2_, p_77654_3_);
	}

	public boolean canBeDepleted() {
		return delegate.canBeDepleted();
	}

	public boolean hurtEnemy(ItemStack p_77644_1_, LivingEntity p_77644_2_, LivingEntity p_77644_3_) {
		return delegate.hurtEnemy(p_77644_1_, p_77644_2_, p_77644_3_);
	}

	public float getXpRepairRatio(ItemStack stack) {
		return delegate.getXpRepairRatio(stack);
	}

	public boolean mineBlock(ItemStack p_179218_1_, World p_179218_2_, BlockState p_179218_3_, BlockPos p_179218_4_,
			LivingEntity p_179218_5_) {
		return delegate.mineBlock(p_179218_1_, p_179218_2_, p_179218_3_, p_179218_4_, p_179218_5_);
	}

	public CompoundNBT getShareTag(ItemStack stack) {
		return delegate.getShareTag(stack);
	}

	public boolean isCorrectToolForDrops(BlockState p_150897_1_) {
		return delegate.isCorrectToolForDrops(p_150897_1_);
	}

	public ActionResultType interactLivingEntity(ItemStack p_111207_1_, PlayerEntity p_111207_2_,
			LivingEntity p_111207_3_, Hand p_111207_4_) {
		return delegate.interactLivingEntity(p_111207_1_, p_111207_2_, p_111207_3_, p_111207_4_);
	}

	public ITextComponent getDescription() {
		return delegate.getDescription();
	}

	public String toString() {
		return delegate.toString();
	}

	public String getDescriptionId() {
		return delegate.getDescriptionId();
	}

	public void readShareTag(ItemStack stack, CompoundNBT nbt) {
		delegate.readShareTag(stack, nbt);
	}

	public String getDescriptionId(ItemStack p_77667_1_) {
		return delegate.getDescriptionId(p_77667_1_);
	}

	public boolean shouldOverrideMultiplayerNbt() {
		return delegate.shouldOverrideMultiplayerNbt();
	}

	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		return delegate.onBlockStartBreak(itemstack, pos, player);
	}

	@SuppressWarnings("deprecation")
	public boolean hasCraftingRemainingItem() {
		return delegate.hasCraftingRemainingItem();
	}

	public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_,
			boolean p_77663_5_) {
		delegate.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
	}

	public void onCraftedBy(ItemStack p_77622_1_, World p_77622_2_, PlayerEntity p_77622_3_) {
		delegate.onCraftedBy(p_77622_1_, p_77622_2_, p_77622_3_);
	}

	public boolean isComplex() {
		return delegate.isComplex();
	}

	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return delegate.getUseAnimation(p_77661_1_);
	}

	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		delegate.onUsingTick(stack, player, count);
	}

	public int getUseDuration(ItemStack p_77626_1_) {
		return delegate.getUseDuration(p_77626_1_);
	}

	public void releaseUsing(ItemStack p_77615_1_, World p_77615_2_, LivingEntity p_77615_3_, int p_77615_4_) {
		delegate.releaseUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
	}

	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		return delegate.onLeftClickEntity(stack, player, entity);
	}

	public void appendHoverText(ItemStack p_77624_1_, World p_77624_2_, List<ITextComponent> p_77624_3_,
			ITooltipFlag p_77624_4_) {
		delegate.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

	public ITextComponent getName(ItemStack p_200295_1_) {
		return delegate.getName(p_200295_1_);
	}

	public boolean isFoil(ItemStack p_77636_1_) {
		return delegate.isFoil(p_77636_1_);
	}

	public Rarity getRarity(ItemStack p_77613_1_) {
		return delegate.getRarity(p_77613_1_);
	}

	public ItemStack getContainerItem(ItemStack itemStack) {
		return delegate.getContainerItem(itemStack);
	}

	public boolean isEnchantable(ItemStack p_77616_1_) {
		return delegate.isEnchantable(p_77616_1_);
	}

	public boolean hasContainerItem(ItemStack stack) {
		return delegate.hasContainerItem(stack);
	}

	public int getEntityLifespan(ItemStack itemStack, World world) {
		return delegate.getEntityLifespan(itemStack, world);
	}

	public boolean hasCustomEntity(ItemStack stack) {
		return delegate.hasCustomEntity(stack);
	}

	public int getEnchantmentValue() {
		return delegate.getEnchantmentValue();
	}

	public void fillItemCategory(ItemGroup p_150895_1_, NonNullList<ItemStack> p_150895_2_) {
		delegate.fillItemCategory(p_150895_1_, p_150895_2_);
	}

	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		return delegate.createEntity(world, location, itemstack);
	}

	public boolean isValidRepairItem(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		return delegate.isValidRepairItem(p_82789_1_, p_82789_2_);
	}

	@SuppressWarnings("deprecation")
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType p_111205_1_) {
		return delegate.getDefaultAttributeModifiers(p_111205_1_);
	}

	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		return delegate.onEntityItemUpdate(stack, entity);
	}

	public Collection<ItemGroup> getCreativeTabs() {
		return delegate.getCreativeTabs();
	}

	public boolean isRepairable(ItemStack stack) {
		return delegate.isRepairable(stack);
	}

	public Set<ToolType> getToolTypes(ItemStack stack) {
		return delegate.getToolTypes(stack);
	}

	public int getHarvestLevel(ItemStack stack, ToolType tool, PlayerEntity player, BlockState blockState) {
		return delegate.getHarvestLevel(stack, tool, player, blockState);
	}

	public float getSmeltingExperience(ItemStack item) {
		return delegate.getSmeltingExperience(item);
	}

	public Set<ResourceLocation> getTags() {
		return delegate.getTags();
	}

	public boolean doesSneakBypassUse(ItemStack stack, IWorldReader world, BlockPos pos, PlayerEntity player) {
		return delegate.doesSneakBypassUse(stack, world, pos, player);
	}

	public boolean useOnRelease(ItemStack p_219970_1_) {
		return delegate.useOnRelease(p_219970_1_);
	}

	public ItemStack getDefaultInstance() {
		return delegate.getDefaultInstance();
	}

	public boolean is(ITag<Item> p_206844_1_) {
		return delegate.is(p_206844_1_);
	}

	public boolean isEdible() {
		return delegate.isEdible();
	}

	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		delegate.onArmorTick(stack, world, player);
	}

	public Food getFoodProperties() {
		return delegate.getFoodProperties();
	}

	public SoundEvent getDrinkingSound() {
		return delegate.getDrinkingSound();
	}

	public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
		return delegate.canEquip(stack, armorType, entity);
	}

	public SoundEvent getEatingSound() {
		return delegate.getEatingSound();
	}

	public boolean isFireResistant() {
		return delegate.isFireResistant();
	}

	public boolean canBeHurtBy(DamageSource p_234685_1_) {
		return delegate.canBeHurtBy(p_234685_1_);
	}

	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return delegate.getEquipmentSlot(stack);
	}

	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return delegate.isBookEnchantable(stack, book);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return delegate.getArmorTexture(stack, entity, slot, type);
	}

	public FontRenderer getFontRenderer(ItemStack stack) {
		return delegate.getFontRenderer(stack);
	}

	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {
		return delegate.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		return delegate.onEntitySwing(stack, entity);
	}

	public void renderHelmetOverlay(ItemStack stack, PlayerEntity player, int width, int height, float partialTicks) {
		delegate.renderHelmetOverlay(stack, player, width, height, partialTicks);
	}

	public int getDamage(ItemStack stack) {
		return delegate.getDamage(stack);
	}

	public boolean showDurabilityBar(ItemStack stack) {
		return delegate.showDurabilityBar(stack);
	}

	public double getDurabilityForDisplay(ItemStack stack) {
		return delegate.getDurabilityForDisplay(stack);
	}

	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return delegate.getRGBDurabilityForDisplay(stack);
	}

	public int getMaxDamage(ItemStack stack) {
		return delegate.getMaxDamage(stack);
	}

	public boolean isDamaged(ItemStack stack) {
		return delegate.isDamaged(stack);
	}

	public void setDamage(ItemStack stack, int damage) {
		delegate.setDamage(stack, damage);
	}

	public boolean canHarvestBlock(ItemStack stack, BlockState state) {
		return delegate.canHarvestBlock(stack, state);
	}

	public int getItemStackLimit(ItemStack stack) {
		return delegate.getItemStackLimit(stack);
	}

	public int getItemEnchantability(ItemStack stack) {
		return delegate.getItemEnchantability(stack);
	}

	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return delegate.canApplyAtEnchantingTable(stack, enchantment);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return delegate.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
	}

	public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
		return delegate.shouldCauseBlockBreakReset(oldStack, newStack);
	}

	public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
		return delegate.canContinueUsing(oldStack, newStack);
	}

	public String getCreatorModId(ItemStack itemStack) {
		return delegate.getCreatorModId(itemStack);
	}

	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return delegate.initCapabilities(stack, nbt);
	}

	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return delegate.canDisableShield(stack, shield, entity, attacker);
	}

	public boolean isShield(ItemStack stack, LivingEntity entity) {
		return delegate.isShield(stack, entity);
	}

	public int getBurnTime(ItemStack itemStack) {
		return delegate.getBurnTime(itemStack);
	}

	public void onHorseArmorTick(ItemStack stack, World world, MobEntity horse) {
		delegate.onHorseArmorTick(stack, world, horse);
	}

	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return delegate.damageItem(stack, amount, entity, onBroken);
	}

	public boolean isEnderMask(ItemStack stack, PlayerEntity player, EndermanEntity endermanEntity) {
		return delegate.isEnderMask(stack, player, endermanEntity);
	}

	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return delegate.canElytraFly(stack, entity);
	}

	public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
		return delegate.elytraFlightTick(stack, entity, flightTicks);
	}

	public boolean isDamageable(ItemStack stack) {
		return delegate.isDamageable(stack);
	}

}
