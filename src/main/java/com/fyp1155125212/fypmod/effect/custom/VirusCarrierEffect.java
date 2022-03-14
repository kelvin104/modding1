package com.fyp1155125212.fypmod.effect.custom;

import com.fyp1155125212.fypmod.entity.custom.CoughEntity;
import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nullable;


public class VirusCarrierEffect extends Effect {
    public VirusCarrierEffect() {
        super(EffectType.NEUTRAL, 12729633);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        //if(Math.random() < poissonDistributionNumber(entityLivingBaseIn)){ //replace this with poisson distribution
          if(true){
            //virus carrier effect time = 1500
            entityLivingBaseIn.playSound(SoundEvents.ENTITY_SHEEP_DEATH,1000,1000);
            //CoughEntity.applySicknessEffect(entityLivingBaseIn, CoughEntity.multiplierForSicknessEffect(null, entityLivingBaseIn));
            entityLivingBaseIn.addPotionEffect(new EffectInstance(EffectInit.SICKNESS.get(), 99999));
        }

    }

    @Override
    public void affectEntity(@Nullable Entity p_180793_1_, @Nullable Entity p_180793_2_, LivingEntity p_180793_3_, int p_180793_4_, double p_180793_5_) {
        super.affectEntity(p_180793_1_, p_180793_2_, p_180793_3_, p_180793_4_, p_180793_5_);
        performEffect(p_180793_3_,p_180793_4_);
    }

    private double poissonDistributionNumber(LivingEntity entityLivingBaseIn){
        //Lambda = 3,
        double result;
        double a,b,c;
        int x;
        double remaining_effect_time = entityLivingBaseIn.getActivePotionEffect(EffectInit.VIRUS_CARRIER.get()).getDuration();
        if(remaining_effect_time>1500){entityLivingBaseIn.playSound(SoundEvents.ENTITY_PIG_HURT,100,100);}
        x = (int)((1500-remaining_effect_time)/100);
        if(x<0){x=0;}
        a = Math.pow(3,x);
        b = Math.exp(-3);
        c = factorial(x);
        result = (a*b)/c;
        return result;
    }
    private static int factorial(int n)
    {
        if (n == 0)
            return 1;
        return n * factorial(n-1);
    }


}