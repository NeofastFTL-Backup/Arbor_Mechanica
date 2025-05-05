package com.neofast.arbor_mechanica.ecoforgeupdate.compat;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.ecoforgeupdate.block.Blocks2;
import com.neofast.arbor_mechanica.ecoforgeupdate.recipes.NaturaCollectorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class NaturaCollectorRecipeCategory implements IRecipeCategory<NaturaCollectorRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID, "natura_collector_recipe");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID,
            "textures/gui/nt_machineentity1/cutting_machine_gui.png");

    public static final RecipeType<NaturaCollectorRecipe> NATURA_COLLECTOR_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, NaturaCollectorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public NaturaCollectorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0 ,0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks2.CUTTINGMACHINE));
    }

    @Override
    public RecipeType<NaturaCollectorRecipe> getRecipeType() {
        return NATURA_COLLECTOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.arbor_mechanica.natura_collector");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, NaturaCollectorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}
