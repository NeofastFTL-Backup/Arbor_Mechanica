package com.neofast.arbor_mechanica.block.entities;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class MossyConduitBlockEntity extends BlockEntity {
    public int tickCount;
    private float activeRotation;
    private boolean isActive;
    private boolean isHunting;
    private final List<BlockPos> effectBlocks = Lists.newArrayList();
    @Nullable
    private LivingEntity destroyTarget;
    @Nullable
    private UUID destroyTargetUUID;
    private long nextAmbientSoundActivation;


    public MossyConduitBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TileEntities.MOSSY_CONDUIT_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, MossyConduitBlockEntity pBlockEntity) {
        ++pBlockEntity.tickCount;
        long i = pLevel.getGameTime();
        List<BlockPos> list = pBlockEntity.effectBlocks;
        if (i % 40L == 0L) {
            pBlockEntity.isActive = updateShape(pLevel, pPos, list);
            updateHunting(pBlockEntity, list);
        }

        updateClientTarget(pLevel, pPos, pBlockEntity);
        animationTick(pLevel, pPos, list, pBlockEntity.destroyTarget, pBlockEntity.tickCount);
        if (pBlockEntity.isActive()) {
            ++pBlockEntity.activeRotation;
        }

    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, MossyConduitBlockEntity pBlockEntity) {
        ++pBlockEntity.tickCount;
        long i = pLevel.getGameTime();
        List<BlockPos> list = pBlockEntity.effectBlocks;
        if (i % 40L == 0L) {
            boolean flag = updateShape(pLevel, pPos, list);
            if (flag != pBlockEntity.isActive) {
                SoundEvent soundevent = flag ? SoundEvents.CONDUIT_ACTIVATE : SoundEvents.CONDUIT_DEACTIVATE;
                pLevel.playSound((Player)null, pPos, soundevent, SoundSource.BLOCKS, 0.5F, 1.0F);
            }

            pBlockEntity.isActive = flag;
            updateHunting(pBlockEntity, list);
            if (flag) {
                applyEffects(pLevel, pPos, list);
                updateDestroyTarget(pLevel, pPos, pState, list, pBlockEntity);
            }
        }



        if (pBlockEntity.isActive()) {
            if (i % 80L == 0L) {
                pLevel.playSound((Player)null, pPos, SoundEvents.CONDUIT_AMBIENT, SoundSource.BLOCKS, 0.5F, 1.0F);
            }

            if (i > pBlockEntity.nextAmbientSoundActivation) {
                pBlockEntity.nextAmbientSoundActivation = i + 60L + (long)pLevel.getRandom().nextInt(40);
                pLevel.playSound((Player)null, pPos, SoundEvents.CONDUIT_AMBIENT_SHORT, SoundSource.BLOCKS, 0.5F, 1.0F);
            }
        }

    }

    private static void updateHunting(MossyConduitBlockEntity pBlockEntity, List<BlockPos> pPositions) {
        pBlockEntity.setHunting(pPositions.size() >= 42);
    }

    //

    //
    private static boolean updateShape(Level pLevel, BlockPos pPos, List<BlockPos> pPositions) {
        pPositions.clear();

        for(int j1 = -2; j1 <= 2; ++j1) {
            for(int k1 = -2; k1 <= 2; ++k1) {
                for(int l1 = -2; l1 <= 2; ++l1) {
                    int i2 = Math.abs(j1);
                    int l = Math.abs(k1);
                    int i1 = Math.abs(l1);
                    if ((i2 > 1 || l > 1 || i1 > 1) && (j1 == 0 && (l == 2 || i1 == 2) || k1 == 0 && (i2 == 2 || i1 == 2) || l1 == 0 && (i2 == 2 || l == 2))) {
                        BlockPos blockpos1 = pPos.offset(j1, k1, l1);
                        BlockState blockstate = pLevel.getBlockState(blockpos1);

                        if (isMossyConduitFrameMossyStoneBrickMossCarpet(blockstate, pLevel, blockpos1, pPos)) {
                            pPositions.add(blockpos1);
                        }
                    }
                }
            }
        }
        return pPositions.size() >= 16;
    }
    private static void applyEffects(Level pLevel, BlockPos pPos, List<BlockPos> pPositions) {
        int i = pPositions.size();
        int j = i / 7 * 10;
        int k = pPos.getX();
        int l = pPos.getY();
        int i1 = pPos.getZ();
        AABB aabb = (new AABB((double)k, (double)l, (double)i1, (double)(k + 1), (double)(l + 1), (double)(i1 + 1))).inflate((double)j).expandTowards(0.0D, (double)pLevel.getHeight(), 0.0D);
        List<Player> list = pLevel.getEntitiesOfClass(Player.class, aabb);
        if (!list.isEmpty()) {
            for(Player player : list) {
                if (pPos.closerThan(player.blockPosition(), (double)j)) {
                    player.addEffect(new MobEffectInstance(MobEffects.HEAL, 260, 2, true, false, true));
                }
            }

        }
    }

    private static boolean isMossyConduitFrameMossyStoneBrickMossCarpet(BlockState state, LevelReader level, BlockPos pos, BlockPos conduit)
    {
        return state.getBlock() == Blocks.MOSS_CARPET ||
                state.getBlock() == Blocks.MOSSY_STONE_BRICKS;
    }


    private static void updateDestroyTarget(Level pLevel, BlockPos pPos, BlockState pState, List<BlockPos> pPositions, MossyConduitBlockEntity pBlockEntity) {
        LivingEntity livingentity = pBlockEntity.destroyTarget;
        int i = pPositions.size();
        if (i < 42) {
            pBlockEntity.destroyTarget = null;
        } else if (pBlockEntity.destroyTarget == null && pBlockEntity.destroyTargetUUID != null) {
            pBlockEntity.destroyTarget = findDestroyTarget(pLevel, pPos, pBlockEntity.destroyTargetUUID);
            pBlockEntity.destroyTargetUUID = null;
        } else if (pBlockEntity.destroyTarget == null) {
            List<LivingEntity> list = pLevel.getEntitiesOfClass(LivingEntity.class, getDestroyRangeAABB(pPos), (p_289511_) -> {
                return p_289511_ instanceof Phantom;
            });
            if (!list.isEmpty()) {
                pBlockEntity.destroyTarget = list.get(pLevel.random.nextInt(list.size()));
            }
        } else if (!pBlockEntity.destroyTarget.isAlive() || !pPos.closerThan(pBlockEntity.destroyTarget.blockPosition(), 8.0D)) {
            pBlockEntity.destroyTarget = null;
        }

        if (pBlockEntity.destroyTarget != null) {
            pLevel.playSound((Player)null, pBlockEntity.destroyTarget.getX(), pBlockEntity.destroyTarget.getY(), pBlockEntity.destroyTarget.getZ(), SoundEvents.CONDUIT_ATTACK_TARGET, SoundSource.BLOCKS, 1.0F, 1.0F);
            pBlockEntity.destroyTarget.hurt(pLevel.damageSources().magic(), 4.0F);
        }

        if (livingentity != pBlockEntity.destroyTarget) {
            pLevel.sendBlockUpdated(pPos, pState, pState, 2);
        }

    }

    private static void updateClientTarget(Level pLevel, BlockPos pPos, MossyConduitBlockEntity pBlockEntity) {
        if (pBlockEntity.destroyTargetUUID == null) {
            pBlockEntity.destroyTarget = null;
        } else if (pBlockEntity.destroyTarget == null || !pBlockEntity.destroyTarget.getUUID().equals(pBlockEntity.destroyTargetUUID)) {
            pBlockEntity.destroyTarget = findDestroyTarget(pLevel, pPos, pBlockEntity.destroyTargetUUID);
            if (pBlockEntity.destroyTarget == null) {
                pBlockEntity.destroyTargetUUID = null;
            }
        }

    }

    private static AABB getDestroyRangeAABB(BlockPos pPos) {
        int i = pPos.getX();
        int j = pPos.getY();
        int k = pPos.getZ();
        return (new AABB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1))).inflate(20.0D);
    }

    @Nullable
    private static LivingEntity findDestroyTarget(Level pLevel, BlockPos pPos, UUID pTargetId) {
        List<LivingEntity> list = pLevel.getEntitiesOfClass(LivingEntity.class, getDestroyRangeAABB(pPos), (p_289510_) -> {
            return p_289510_.getUUID().equals(pTargetId);
        });
        return list.size() == 1 ? list.get(0) : null;
    }

    private static void animationTick(Level pLevel, BlockPos pPos, List<BlockPos> pPositions, @Nullable Entity pEntity, int pTickCount) {
        RandomSource randomsource = pLevel.random;
        double d0 = (double)(Mth.sin((float)(pTickCount + 35) * 0.1F) / 2.0F + 0.5F);
        d0 = (d0 * d0 + d0) * (double)0.3F;
        Vec3 vec3 = new Vec3((double)pPos.getX() + 0.5D, (double)pPos.getY() + 1.5D + d0, (double)pPos.getZ() + 0.5D);

        for(BlockPos blockpos : pPositions) {
            if (randomsource.nextInt(50) == 0) {
                BlockPos blockpos1 = blockpos.subtract(pPos);
                float f = -0.5F + randomsource.nextFloat() + (float)blockpos1.getX();
                float f1 = -2.0F + randomsource.nextFloat() + (float)blockpos1.getY();
                float f2 = -0.5F + randomsource.nextFloat() + (float)blockpos1.getZ();
                pLevel.addParticle(ParticleTypes.HAPPY_VILLAGER, vec3.x, vec3.y, vec3.z, (double)f, (double)f1, (double)f2);
            }
        }

        if (pEntity != null) {
            Vec3 vec31 = new Vec3(pEntity.getX(), pEntity.getEyeY(), pEntity.getZ());
            float f3 = (-0.5F + randomsource.nextFloat()) * (3.0F + pEntity.getBbWidth());
            float f4 = -1.0F + randomsource.nextFloat() * pEntity.getBbHeight();
            float f5 = (-0.5F + randomsource.nextFloat()) * (3.0F + pEntity.getBbWidth());
            Vec3 vec32 = new Vec3((double)f3, (double)f4, (double)f5);
            pLevel.addParticle(ParticleTypes.HAPPY_VILLAGER, vec31.x, vec31.y, vec31.z, vec32.x, vec32.y, vec32.z);
        }

    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isHunting() {
        return this.isHunting;
    }

    private void setHunting(boolean pIsHunting) {
        this.isHunting = pIsHunting;
    }

    public float getActiveRotation(float pPartialTick) {
        return (this.activeRotation + pPartialTick) * -0.0375F;
    }
}
