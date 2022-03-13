package com.fyp1155125212.fypmod.effect.custom;

import com.fyp1155125212.fypmod.entity.custom.CoughEntity;
import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;


public class VirusCarrierEffect extends Effect {
    public VirusCarrierEffect() {
        super(EffectType.NEUTRAL, 12729633);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        if(Math.random() < 0.1){ //replace this with poisson distribution
            CoughEntity.applySicknessEffect(entityLivingBaseIn, CoughEntity.multiplierForSicknessEffect(null, entityLivingBaseIn));
        }

    }


}