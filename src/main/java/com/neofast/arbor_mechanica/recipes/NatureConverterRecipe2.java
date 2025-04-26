package com.neofast.arbor_mechanica.recipes;

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

public record NatureConverterRecipe2(Ingredient inputItem, Ingredient inputItem2, ItemStack output) implements Recipe<NatureConverterRecipeInput2> {
    // inputItem & output ==> Read From JSON File!
    // GrowthChamberRecipeInput --> INVENTORY of the Block Entity

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        list.add(inputItem2);
        return list;
    }

    @Override
    public boolean matches(NatureConverterRecipeInput2 natureConverterRecipeInput2, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return inputItem.test(natureConverterRecipeInput2.getItem(0)) && inputItem2.test(natureConverterRecipeInput2.getItem(1));
    }

    @Override
    public ItemStack assemble(NatureConverterRecipeInput2 natureConverterRecipeInput2, HolderLookup.Provider provider) {
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
        return Recipes.NATURA_CONVERTER2_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return Recipes.NATURA_CONVERTER2_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<NatureConverterRecipe2> {
        public static final MapCodec<NatureConverterRecipe2> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(NatureConverterRecipe2::inputItem),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient2").forGetter(NatureConverterRecipe2::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(NatureConverterRecipe2::output)
        ).apply(inst, NatureConverterRecipe2::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, NatureConverterRecipe2> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, NatureConverterRecipe2::inputItem,
                        Ingredient.CONTENTS_STREAM_CODEC, NatureConverterRecipe2::inputItem2,
                        ItemStack.STREAM_CODEC, NatureConverterRecipe2::output,
                        NatureConverterRecipe2::new);

        @Override
        public MapCodec<NatureConverterRecipe2> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, NatureConverterRecipe2> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
