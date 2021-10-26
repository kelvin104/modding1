package com.fyp1155125212.fypmod.entity.custom;

import com.fyp1155125212.fypmod.init.EntityTypesInit;
import com.fyp1155125212.fypmod.item.custom.complex_item_one_class;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CoughEntity extends ProjectileEntity {

    public CoughEntity(EntityType<? extends CoughEntity> p_i50162_1_, World p_i50162_2_) {
        super(p_i50162_1_, p_i50162_2_);
    }

    public CoughEntity(World worldIn, NeutralCitizen_N p_i47273_2_) {
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
        if (entity instanceof LivingEntity && !(targeted_entity instanceof PlayerEntity)) {
            targeted_entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, (LivingEntity)entity).setProjectile(), 1.0F);
        }
        if (entity instanceof LivingEntity && targeted_entity instanceof PlayerEntity) {

            targeted_entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, (LivingEntity)entity).setProjectile(), 0F);
            complex_item_one_class.applyEffect2((PlayerEntity)result.getEntity(), 99999);
            playSound(SoundEvents.ENTITY_GHAST_SCREAM,0.2F,0.2F);
        }

    }

    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        if (!this.world.isRemote) {
            this.remove();
        }

    }

    protected void registerData() {
    }

    public IPacket<?> createSpawnPacket() {
        return new SSpawnObjectPacket(this);
    }
}
