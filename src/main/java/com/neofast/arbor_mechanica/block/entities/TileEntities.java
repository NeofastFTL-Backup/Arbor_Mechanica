package com.neofast.arbor_mechanica.block.entities;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.block.Blocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TileEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ArborMechanica.MOD_ID);

    public static final Supplier<BlockEntityType<NT_MachineEntity1>> NT_MACHINEENTITY1 =
            BLOCK_ENTITIES.register("nt_machineentity1", () -> BlockEntityType.Builder.of(
                    NT_MachineEntity1::new, Blocks.NT_MACHINE1.get()).build(null));

    public static final Supplier<BlockEntityType<NT_MachineEntity2>> NT_MACHINEENTITY2 =
            BLOCK_ENTITIES.register("nt_machineentity2", () -> BlockEntityType.Builder.of(
                    NT_MachineEntity2::new, Blocks.NT_MACHINE2.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
