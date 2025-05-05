package com.neofast.arbor_mechanica.ecoforgeupdate.compat;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.ecoforgeupdate.block.Blocks2;
import com.neofast.arbor_mechanica.ecoforgeupdate.network.custom.CuttingMachineScreen;
import com.neofast.arbor_mechanica.ecoforgeupdate.network.custom.NT_Machine1Screen;
import com.neofast.arbor_mechanica.ecoforgeupdate.network.custom.NT_Machine2Screen;
import com.neofast.arbor_mechanica.ecoforgeupdate.network.custom.NaturaCollectorScreen;
import com.neofast.arbor_mechanica.ecoforgeupdate.recipes.*;
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

        registration.addRecipeCategories(new NatureConverterRecipeCategory2(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new CuttingMachineRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new NaturaCollectorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<NatureConverterRecipe> natureConverterRecipeCategory = recipeManager
                .getAllRecipesFor(Recipes.NATURA_CONVERTER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(NatureConverterRecipeCategory.NATURA_CONVERTER_RECIPE_RECIPE_TYPE, natureConverterRecipeCategory);

        List<NatureConverterRecipe2> natureConverter2RecipeCategory = recipeManager
                .getAllRecipesFor(Recipes.NATURA_CONVERTER_TYPE2.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(NatureConverterRecipeCategory2.NATURA_CONVERTER_RECIPE_RECIPE_TYPE2, natureConverter2RecipeCategory);

        List<CuttingMachineRecipe> cuttingMachineRecipeCategory = recipeManager
                .getAllRecipesFor(Recipes.CUTTINGMACHINE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(CuttingMachineRecipeCategory.CUTTING_MACHINE_RECIPE_RECIPE_TYPE, cuttingMachineRecipeCategory);

        List<NaturaCollectorRecipe> naturaCollectorRecipe = recipeManager
                .getAllRecipesFor(Recipes.NATURA_COLLECTOR_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(NaturaCollectorRecipeCategory.NATURA_COLLECTOR_RECIPE_RECIPE_TYPE, naturaCollectorRecipe);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(NT_Machine1Screen.class, 74, 30, 22, 20,
                NatureConverterRecipeCategory.NATURA_CONVERTER_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(NT_Machine2Screen.class, 74, 30, 22, 20,
                NatureConverterRecipeCategory2.NATURA_CONVERTER_RECIPE_RECIPE_TYPE2);

        registration.addRecipeClickArea(CuttingMachineScreen.class, 74, 30, 22, 20,
                CuttingMachineRecipeCategory.CUTTING_MACHINE_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(NaturaCollectorScreen.class, 74, 30, 22, 20,
                NaturaCollectorRecipeCategory.NATURA_COLLECTOR_RECIPE_RECIPE_TYPE);


    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Blocks2.NT_MACHINE1.get().asItem()),
                NatureConverterRecipeCategory.NATURA_CONVERTER_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(Blocks2.NT_MACHINE2.get().asItem()),
                NatureConverterRecipeCategory2.NATURA_CONVERTER_RECIPE_RECIPE_TYPE2);

        registration.addRecipeCatalyst(new ItemStack(Blocks2.CUTTINGMACHINE.get().asItem()),
                CuttingMachineRecipeCategory.CUTTING_MACHINE_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(Blocks2.NATURA_COLLECTOR.get().asItem()),
                NaturaCollectorRecipeCategory.NATURA_COLLECTOR_RECIPE_RECIPE_TYPE);
    }
}
