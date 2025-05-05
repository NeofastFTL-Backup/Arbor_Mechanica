package com.neofast.arbor_mechanica.ecoforgeupdate.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record CuttingMachineRecipe(Ingredient inputItem, ItemStack output) implements Recipe<CuttingMachineRecipeInput> {
    // inputItem & output ==> Read From JSON File!
    // GrowthChamberRecipeInput --> INVENTORY of the Block Entity

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(CuttingMachineRecipeInput cuttingMachineRecipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return inputItem.test(cuttingMachineRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(CuttingMachineRecipeInput cuttingMachineRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.CUTTINGMACHINE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return Recipes.CUTTINGMACHINE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<CuttingMachineRecipe> {
        public static final MapCodec<CuttingMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CuttingMachineRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CuttingMachineRecipe::output)
        ).apply(inst, CuttingMachineRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CuttingMachineRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, CuttingMachineRecipe::inputItem,
                        ItemStack.STREAM_CODEC, CuttingMachineRecipe::output,
                        CuttingMachineRecipe::new);

        @Override
        public MapCodec<CuttingMachineRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CuttingMachineRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
