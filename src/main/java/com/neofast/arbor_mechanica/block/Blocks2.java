package com.neofast.arbor_mechanica.block;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.block.entities.*;
import com.neofast.arbor_mechanica.energy.NaturaCollectorBlock;
import com.neofast.arbor_mechanica.item.Items;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Blocks2 {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ArborMechanica.MOD_ID);

    public static final DeferredBlock<Block> WOODEN_GEAR_BOX = registerBlock("wooden_gear_box",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> MOSSY_CORE = registerBlock("mossy_core",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.CHERRY_LEAVES)));

    public static final DeferredBlock<Block> NT_MACHINE1 = registerBlock("nt_machine1",
            () -> new NT_Machine1(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> NT_MACHINE2 = registerBlock("nt_machine2",
            () -> new NT_Machine2(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> CUTTINGMACHINE = registerBlock("cuttingmachine",
            () -> new CuttingMachine(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> NATURA_COLLECTOR = registerBlock("natura_collector",
            () -> new NaturaCollectorBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> ROOTED_CONDUIT = registerBlock("rooted_conduit",
            () -> new RootedConduitBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CONDUIT).noOcclusion()));

    public static final DeferredBlock<Block> FROSTING_CONDUIT = registerBlock("frosting_conduit",
            () -> new FrostingConduitBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CONDUIT).noOcclusion()));

    public static final DeferredBlock<Block> MOSSY_CONDUIT = registerBlock("mossy_conduit",
            () -> new MossyConduitBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CONDUIT).noOcclusion()));


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
