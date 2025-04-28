package com.neofast.arbor_mechanica.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class NaturaCollectorBlockTickUpdater {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world instanceof Level _lvl1 && _lvl1.isDay() && new Object() {
			public boolean canReceiveEnergy(LevelAccessor level, BlockPos pos) {
				if (level instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, pos, Direction.UP);
					if (_entityStorage != null)
						return _entityStorage.canReceive();
				}
				return false;
			}
		}.canReceiveEnergy(world, BlockPos.containing(x, y - 1, z))) {
			if (world instanceof ILevelExtension _ext) {
				IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y - 1, z), Direction.UP);
				if (_entityStorage != null)
					_entityStorage.receiveEnergy(100, false);
			}
		}
	}
}
