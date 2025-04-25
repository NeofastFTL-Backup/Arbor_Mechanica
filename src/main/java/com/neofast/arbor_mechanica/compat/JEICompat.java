package com.neofast.arbor_mechanica.compat;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.block.Blocks;
import com.neofast.arbor_mechanica.network.custom.NT_Machine1Screen;
import com.neofast.arbor_mechanica.recipes.NatureConverterRecipe;
import com.neofast.arbor_mechanica.recipes.Recipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEICompat implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(ArborMechanica.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new NatureConverterRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<NatureConverterRecipe> natureConverterRecipeCategory = recipeManager
                .getAllRecipesFor(Recipes.NATURA_CONVERTER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(NatureConverterRecipeCategory.NATURA_CONVERTER_RECIPE_RECIPE_TYPE, natureConverterRecipeCategory);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(NT_Machine1Screen.class, 74, 30, 22, 20,
                NatureConverterRecipeCategory.NATURA_CONVERTER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Blocks.NT_MACHINE1.get().asItem()),
                NatureConverterRecipeCategory.NATURA_CONVERTER_RECIPE_RECIPE_TYPE);
    }
}
