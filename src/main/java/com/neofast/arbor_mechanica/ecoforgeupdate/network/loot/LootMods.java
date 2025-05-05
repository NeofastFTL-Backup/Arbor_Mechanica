package com.neofast.arbor_mechanica.ecoforgeupdate.network.loot;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.ecoforgeupdate.item.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class LootMods extends GlobalLootModifierProvider {
    public LootMods(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ArborMechanica.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("plant_fibers_to_short_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, Items.PLANT_FIBERS.get()));
        this.add("plant_fibers_to_tall_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, Items.PLANT_FIBERS.get()));
    }
}
