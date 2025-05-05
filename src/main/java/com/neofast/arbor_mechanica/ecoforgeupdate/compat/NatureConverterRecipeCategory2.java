package com.neofast.arbor_mechanica.ecoforgeupdate.compat;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.ecoforgeupdate.block.Blocks2;
import com.neofast.arbor_mechanica.ecoforgeupdate.recipes.NatureConverterRecipe2;
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

public class NatureConverterRecipeCategory2 implements IRecipeCategory<NatureConverterRecipe2> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID, "nt_machine2");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID,
            "textures/gui/nt_machineentity1/nt_machine2_gui.png");

    public static final RecipeType<NatureConverterRecipe2> NATURA_CONVERTER_RECIPE_RECIPE_TYPE2 =
            new RecipeType<>(UID, NatureConverterRecipe2.class);

    private final IDrawable background;
    private final IDrawable icon;

    public NatureConverterRecipeCategory2(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0 ,0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks2.NT_MACHINE2));
    }

    @Override
    public RecipeType<NatureConverterRecipe2> getRecipeType() {
        return NATURA_CONVERTER_RECIPE_RECIPE_TYPE2;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.arbor_mechanica.nt_machine2");
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
    public void setRecipe(IRecipeLayoutBuilder builder, NatureConverterRecipe2 recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}
