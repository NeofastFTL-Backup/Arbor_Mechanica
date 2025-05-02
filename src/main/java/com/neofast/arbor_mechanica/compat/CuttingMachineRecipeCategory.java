package com.neofast.arbor_mechanica.compat;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.block.Blocks2;
import com.neofast.arbor_mechanica.recipes.CuttingMachineRecipe;
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

public class CuttingMachineRecipeCategory implements IRecipeCategory<CuttingMachineRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID, "cuttingmachine_recipe");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID,
            "textures/gui/nt_machineentity1/cutting_machine_gui.png");

    public static final RecipeType<CuttingMachineRecipe> CUTTING_MACHINE_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, CuttingMachineRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CuttingMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0 ,0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks2.CUTTINGMACHINE));
    }

    @Override
    public RecipeType<CuttingMachineRecipe> getRecipeType() {
        return CUTTING_MACHINE_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.arbor_mechanica.cuttingmachine");
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
    public void setRecipe(IRecipeLayoutBuilder builder, CuttingMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}
