package com.fyp1155125212.fypmod.entity.custom;



import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.UUID;

public class NeutralCitizen_N extends AbstractIllagerEntity implements IAngerable {
    private static final RangedInteger field_234196_bu_ = TickRangeConverter.convertRange(20, 39);
    private int field_234197_bv_;
    private UUID field_234198_bw_;

    public NeutralCitizen_N(EntityType<? extends AbstractIllagerEntity> p_i48556_1_, World p_i48556_2_) {
        super(p_i48556_1_, p_i48556_2_);
    }

    //  @Nullable
    // public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
    //    ILivingEntityData ilivingentitydata = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    //   ((GroundPathNavigator)this.getNavigator()).setBreakDoors(true);
    //  this.setEquipmentBasedOnDifficulty(difficultyIn);
    // this.setEnchantmentBasedOnDifficulty(difficultyIn);
    //return ilivingentitydata;
    // }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, false));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 50.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    public AbstractIllagerEntity.ArmPose getArmPose() {
        return ArmPose.NEUTRAL;
    }

    @Override
    public int getAngerTime() {
        return this.field_234197_bv_;
    }

    @Override
    public void setAngerTime(int time) {
        this.field_234197_bv_ = time;
    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return this.field_234198_bw_;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
        this.field_234198_bw_ = target;
    }

    @Override
    public void func_230258_H__() {
        this.setAngerTime(field_234196_bu_.getRandomWithinRange(this.rand));
    }

    @Override
    public void applyWaveBonus(int wave, boolean p_213660_2_) {

    }

    @Override
    public boolean canJoinRaid() {
        return false;
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, cause)) return;
        if (!this.removed && !this.dead) {
            Entity entity = cause.getTrueSource();
            LivingEntity livingentity = this.getAttackingEntity();
            if (this.scoreValue >= 0 && livingentity != null) {
                livingentity.awardKillScore(this, this.scoreValue, cause);
            }

            if (this.isSleeping()) {
                this.wakeUp();
            }

            this.dead = true;
            this.getCombatTracker().reset();
            if (this.world instanceof ServerWorld) {
                if (entity != null) {
                    entity.onKillEntity((ServerWorld)this.world, this);
                }

                this.spawnDrops(cause);
                this.createWitherRose(livingentity);
            }

            this.world.setEntityState(this, (byte)3);
            this.setPose(Pose.DYING);
        }
    }

    @Override
    public SoundEvent getRaidLossSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if(this.getAngerTime()>0){
            return SoundEvents.ENTITY_PILLAGER_AMBIENT;
        }
        else{
            return SoundEvents.ENTITY_VILLAGER_AMBIENT;
        }

    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return super.getHurtSound(p_184601_1_);
    }
}
