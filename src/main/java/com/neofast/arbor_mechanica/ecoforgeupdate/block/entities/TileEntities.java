package com.neofast.arbor_mechanica.ecoforgeupdate.block.entities;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.ecoforgeupdate.block.Blocks2;
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

    public static final Supplier<BlockEntityType<FrostingConduitBlockEntity>> FROSTING_CONDUIT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("frosting_conduit_block_entity", () -> BlockEntityType.Builder.of(
                    FrostingConduitBlockEntity::new, Blocks2.FROSTING_CONDUIT.get()).build(null));


    public static final Supplier<BlockEntityType<MossyConduitBlockEntity>> MOSSY_CONDUIT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("mossy_conduit_block_entity", () -> BlockEntityType.Builder.of(
                    MossyConduitBlockEntity::new, Blocks2.MOSSY_CONDUIT.get()).build(null));

    public static final Supplier<BlockEntityType<NaturaCollectorBlockEntity>> NATURA_COLLECTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("natura_collector_block_entity", () -> BlockEntityType.Builder.of(
                    NaturaCollectorBlockEntity::new, Blocks2.NATURA_COLLECTOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
