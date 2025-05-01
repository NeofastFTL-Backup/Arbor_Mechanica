package com.neofast.arbor_mechanica.block.entities;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.block.Blocks2;
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
                    NT_MachineEntity1::new, Blocks2.NT_MACHINE1.get()).build(null));

    public static final Supplier<BlockEntityType<NT_MachineEntity2>> NT_MACHINEENTITY2 =
            BLOCK_ENTITIES.register("nt_machineentity2", () -> BlockEntityType.Builder.of(
                    NT_MachineEntity2::new, Blocks2.NT_MACHINE2.get()).build(null));

    public static final Supplier<BlockEntityType<CuttingMachineEntity>> CUTTINGMACHINEENTITY =
            BLOCK_ENTITIES.register("cuttingmachineentity", () -> BlockEntityType.Builder.of(
                    CuttingMachineEntity::new, Blocks2.CUTTINGMACHINE.get()).build(null));

    public static final Supplier<BlockEntityType<RootedConduitBlockEntity>> ROOTED_CONDUIT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("rooted_conduit_block_entity", () -> BlockEntityType.Builder.of(
                    RootedConduitBlockEntity::new, Blocks2.ROOTED_CONDUIT.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
