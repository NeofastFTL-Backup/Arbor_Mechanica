package com.neofast.arbor_mechanica.ecoforgeupdate.recipes;

import com.neofast.arbor_mechanica.ArborMechanica;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Recipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, ArborMechanica.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, ArborMechanica.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CuttingMachineRecipe>> CUTTINGMACHINE_SERIALIZER =
            SERIALIZERS.register("cuttingmachine_recipe", CuttingMachineRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CuttingMachineRecipe>> CUTTINGMACHINE_TYPE =
            TYPES.register("cuttingmachine_recipe", () -> new RecipeType<CuttingMachineRecipe>() {
                @Override
                public String toString() {
                    return "cuttingmachine_recipe";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<NatureConverterRecipe>> NATURA_CONVERTER_SERIALIZER =
            SERIALIZERS.register("natura_converter", NatureConverterRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<NatureConverterRecipe>> NATURA_CONVERTER_TYPE =
            TYPES.register("natura_converter", () -> new RecipeType<NatureConverterRecipe>() {
                @Override
                public String toString() {
                    return "natura_converter";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<NatureConverterRecipe2>> NATURA_CONVERTER_SERIALIZER2 =
            SERIALIZERS.register("natura_converter2", NatureConverterRecipe2.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<NatureConverterRecipe2>> NATURA_CONVERTER_TYPE2 =
            TYPES.register("natura_converter2", () -> new RecipeType<NatureConverterRecipe2>() {
                @Override
                public String toString() {
                    return "natura_converter2";
                }
            });


    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<NaturaCollectorRecipe>> NATURA_COLLECTOR_SERIALIZER =
            SERIALIZERS.register("natura_collector_recipe", NaturaCollectorRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<NaturaCollectorRecipe>> NATURA_COLLECTOR_TYPE =
            TYPES.register("natura_collector_recipe", () -> new RecipeType<NaturaCollectorRecipe>() {
                @Override
                public String toString() {
                    return "natura_collector_recipe";
                }
            });
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
