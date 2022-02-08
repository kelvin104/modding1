package com.fyp1155125212.fypmod.entity.custom;

import com.fyp1155125212.fypmod.entity.custom_entity_goal.HuntInfectedGoal;
import com.fyp1155125212.fypmod.entity.custom_entity_goal.ModNearestAttackableTargetGoal;
import com.fyp1155125212.fypmod.entity.custom_entity_goal.ModRangedCureGoal;
import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;


public class Doctor extends AbstractVillagerEntity implements IAngerable, IRangedAttackMob {
    private static final RangedInteger field_234196_bu_ = TickRangeConverter.convertRange(20, 39);
    private int field_234197_bv_;
    private UUID field_234198_bw_;

    public Doctor(EntityType<? extends AbstractVillagerEntity> p_i48556_1_, World p_i48556_2_) {
        super(p_i48556_1_, p_i48556_2_);
    }

    @Override
    protected void onVillagerTrade(MerchantOffer offer) {

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
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(3, new ModRangedCureGoal(this, 1.0D, 60, 10.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(3, new HuntInfectedGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 70.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    public AbstractIllagerEntity.ArmPose getArmPose() {
        return AbstractIllagerEntity.ArmPose.NEUTRAL;
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
    protected void populateTradeData() {

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

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }


    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        if(target instanceof AbstractVillagerEntity){
            Vector3d vector3d = target.getMotion();
            double d0 = target.getPosX() + vector3d.x - this.getPosX();
            double d1 = target.getPosYEye() - (double)1.1F - this.getPosY();
            double d2 = target.getPosZ() + vector3d.z - this.getPosZ();
            float f = MathHelper.sqrt(d0 * d0 + d2 * d2);
            Potion potion = Potions.WATER;
            this.setAttackTarget((LivingEntity)null);
            PotionEntity potionentity = new PotionEntity(this.world, this);
            potionentity.setItem(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potion));
            potionentity.rotationPitch -= -20.0F;
            potionentity.shoot(d0, d1 + (double)(f * 0.2F), d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0F, 0.8F + this.rand.nextFloat() * 0.4F);
            }

            if(target.isPotionActive(EffectInit.SICKNESS.get())){
                double r = 0.2;
                if(Math.random() < r){target.removeActivePotionEffect(EffectInit.SICKNESS.get());}
            }
            else if(target.isPotionActive(EffectInit.SEVERE_SICKNESS.get())){
                double r = 0.1;
                if(Math.random() < r){target.removeActivePotionEffect(EffectInit.SEVERE_SICKNESS.get());}
            }
            this.world.addEntity(potionentity);
        }

    }
}