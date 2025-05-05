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

public record NatureConverterRecipe(Ingredient inputItem, ItemStack output) implements Recipe<NatureConverterRecipeInput> {
    // inputItem & output ==> Read From JSON File!
    // GrowthChamberRecipeInput --> INVENTORY of the Block Entity

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(NatureConverterRecipeInput natureConverterRecipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return inputItem.test(natureConverterRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(NatureConverterRecipeInput natureConverterRecipeInput, HolderLookup.Provider provider) {
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
        return Recipes.NATURA_CONVERTER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return Recipes.NATURA_CONVERTER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<NatureConverterRecipe> {
        public static final MapCodec<NatureConverterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(NatureConverterRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(NatureConverterRecipe::output)
        ).apply(inst, NatureConverterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, NatureConverterRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, NatureConverterRecipe::inputItem,
                        ItemStack.STREAM_CODEC, NatureConverterRecipe::output,
                        NatureConverterRecipe::new);

        @Override
        public MapCodec<NatureConverterRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, NatureConverterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
