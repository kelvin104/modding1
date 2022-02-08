package com.fyp1155125212.fypmod.entity.custom;

import com.fyp1155125212.fypmod.effect.custom.SicknessEffect;
import com.fyp1155125212.fypmod.init.EffectInit;
import com.fyp1155125212.fypmod.init.EntityTypesInit;
import com.fyp1155125212.fypmod.init.ItemInit;
import com.fyp1155125212.fypmod.item.custom.complex_item_one_class;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;

public class CoughEntity extends ProjectileEntity implements IAngerable {

    private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.createKey(CoughEntity.class, DataSerializers.VARINT);
    private static final RangedInteger ANGER_TIME_RANGE = TickRangeConverter.convertRange(20, 39);
    private UUID field_234231_bH_;

    public CoughEntity(EntityType<? extends CoughEntity> p_i50162_1_, World p_i50162_2_) {
        super(p_i50162_1_, p_i50162_2_);
    }

    public CoughEntity(World worldIn, NeutralCitizen_N p_i47273_2_) {
        this(EntityTypesInit.COUGH.get(), worldIn);
        super.setShooter(p_i47273_2_);
        this.setPosition(p_i47273_2_.getPosX() - (double)(p_i47273_2_.getWidth() + 1.0F) * 0.5D * (double) MathHelper.sin(p_i47273_2_.renderYawOffset * ((float)Math.PI / 180F)), p_i47273_2_.getPosYEye() - (double)0.1F, p_i47273_2_.getPosZ() + (double)(p_i47273_2_.getWidth() + 1.0F) * 0.5D * (double)MathHelper.cos(p_i47273_2_.renderYawOffset * ((float)Math.PI / 180F)));
    }

    public CoughEntity(World worldIn, NeutralCitizen_J p_i47273_2_) {
        this(EntityTypesInit.COUGH.get(), worldIn);
        super.setShooter(p_i47273_2_);
        this.setPosition(p_i47273_2_.getPosX() - (double)(p_i47273_2_.getWidth() + 1.0F) * 0.5D * (double) MathHelper.sin(p_i47273_2_.renderYawOffset * ((float)Math.PI / 180F)), p_i47273_2_.getPosYEye() - (double)0.1F, p_i47273_2_.getPosZ() + (double)(p_i47273_2_.getWidth() + 1.0F) * 0.5D * (double)MathHelper.cos(p_i47273_2_.renderYawOffset * ((float)Math.PI / 180F)));
    }

    @OnlyIn(Dist.CLIENT)
    public CoughEntity(World worldIn, double x, double y, double z, double p_i47274_8_, double p_i47274_10_, double p_i47274_12_) {
        this(EntityTypesInit.COUGH.get(), worldIn);
        this.setPosition(x, y, z);

        for(int i = 0; i < 7; ++i) {
            double d0 = 0.4D + 0.1D * (double)i;
            worldIn.addParticle(ParticleTypes.SPIT, x, y, z, p_i47274_8_ * d0, p_i47274_10_, p_i47274_12_ * d0);

        }

        this.setMotion(p_i47274_8_, p_i47274_10_, p_i47274_12_);
    }

    public void tick() {
        super.tick();
        Vector3d vector3d = this.getMotion();
        RayTraceResult raytraceresult = ProjectileHelper.func_234618_a_(this, this::func_230298_a_);
        if (raytraceresult != null && raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
            this.onImpact(raytraceresult);
        }

        double d0 = this.getPosX() + vector3d.x;
        double d1 = this.getPosY() + vector3d.y;
        double d2 = this.getPosZ() + vector3d.z;
        this.updatePitchAndYaw();
        float f = 0.99F;
        float f1 = 0.06F;
        if (this.world.func_234853_a_(this.getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            this.remove();
        } else if (this.isInWaterOrBubbleColumn()) {
            this.remove();
        } else {
            this.setMotion(vector3d.scale((double)0.99F));
            if (!this.hasNoGravity()) {
                this.setMotion(this.getMotion().add(0.0D, (double)-0.06F, 0.0D));
            }

            this.setPosition(d0, d1, d2);
        }
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        Entity entity = this.getShooter();
        Entity targeted_entity = result.getEntity();
        if (entity instanceof LivingEntity && !(targeted_entity instanceof PlayerEntity)&&!(targeted_entity instanceof AbstractVillagerEntity)) {
            targeted_entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, (LivingEntity)entity).setProjectile(), 5.0F);
        }
        applyVirusCarrierEffect(targeted_entity);

        if (entity instanceof LivingEntity && targeted_entity instanceof AbstractVillagerEntity){

            ((LivingEntity)targeted_entity).addPotionEffect(new EffectInstance(EffectInit.SICKNESS.get(), 99999));
        }
        if (entity instanceof LivingEntity && targeted_entity instanceof PlayerEntity) {
            targeted_entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, (LivingEntity)entity).setProjectile(), 0F);
            //playSound(SoundEvents.ENTITY_GHAST_SCREAM,0.2F,0.2F);
            applySicknessEffect(targeted_entity, multiplierForSicknessEffect(entity, targeted_entity));
        }

        //if(!world.isRemote){this.remove();}

    }

    public void applyVirusCarrierEffect(Entity entity){
        if (entity instanceof PlayerEntity){
            ((LivingEntity)entity).addPotionEffect(new EffectInstance(EffectInit.VIRUS_CARRIER.get(), 1500));
        }
        if (entity instanceof AbstractVillagerEntity){
            ((LivingEntity)entity).addPotionEffect(new EffectInstance(EffectInit.VIRUS_CARRIER.get(), 500));
        }
    }

    public static void applySicknessEffect(Entity entity, double multiplier){
        if(Math.random() < multiplier){
            ((LivingEntity)entity).addPotionEffect(new EffectInstance(EffectInit.SICKNESS.get(), 99999));
            ((LivingEntity)entity).addPotionEffect(new EffectInstance(EffectInit.VIRUS_CARRIER.get(), 99999));
        }
    }

    public static double multiplierForSicknessEffect(Entity attackingEntity, Entity targetedEntity){
        double maskVariable = 1;
        double vaccinatedVariable = 1;
        double virusCarrierVariable = 1;

        if(targetedEntity instanceof PlayerEntity){
            if((((PlayerEntity)targetedEntity).getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK.get())){
                maskVariable = 0.9;
            }
            else if((((PlayerEntity)targetedEntity).getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK_HALF.get())){
                maskVariable = 0.6;
            }
            else if((((PlayerEntity)targetedEntity).getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK_NONE.get())){
                maskVariable = 0.1;
            }
        }

        if((((LivingEntity)targetedEntity).isPotionActive(EffectInit.VACCINATED.get()))){
            vaccinatedVariable = 0; //to be modified
        }

        if((attackingEntity != null)&&(((LivingEntity)attackingEntity).isPotionActive(EffectInit.VIRUS_CARRIER.get()))){
            virusCarrierVariable = 1.5; //to be modified
        }

        return maskVariable * vaccinatedVariable * virusCarrierVariable;
    }




    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        if (!this.world.isRemote) {
            this.remove();
        }

    }

    private boolean isExist(){
        return (getAngerTime() > 0);
    }

    protected void registerData() {
        this.dataManager.register(ANGER_TIME, 0);
    }

    public IPacket<?> createSpawnPacket() {
       // return new SSpawnObjectPacket(this);
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public int getAngerTime() {
        return this.dataManager.get(ANGER_TIME);
    }

    public void setAngerTime(int time) {
        this.dataManager.set(ANGER_TIME, time);
    }

    public void func_230258_H__() {
        this.setAngerTime(ANGER_TIME_RANGE.getRandomWithinRange(this.rand));
    }

    @Nullable
    public UUID getAngerTarget() {
        return this.field_234231_bH_;
    }

    public void setAngerTarget(@Nullable UUID target) {
        this.field_234231_bH_ = target;
    }

    @Override
    public void setRevengeTarget(@Nullable LivingEntity livingBase) {

    }

    @Override
    public void setAttackingPlayer(@Nullable PlayerEntity p_230246_1_) {

    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {

    }

    @Nullable
    @Override
    public LivingEntity getAttackTarget() {
        return null;
    }
}
