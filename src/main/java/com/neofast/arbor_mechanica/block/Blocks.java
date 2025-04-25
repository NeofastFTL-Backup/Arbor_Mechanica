package com.neofast.arbor_mechanica.block;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.block.entities.NT_Machine1;
import com.neofast.arbor_mechanica.item.Items;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Blocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ArborMechanica.MOD_ID);

    public static final DeferredBlock<Block> WOODEN_GEAR_BOX = registerBlock("wooden_gear_box",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> NT_MACHINE1 = registerBlock("nt_machine1",
            () -> new NT_Machine1(BlockBehaviour.Properties.of()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        Items.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
