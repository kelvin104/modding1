package com.fyp1155125212.fypmod.effect.custom;


import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import java.util.Random;


public class SicknessEffect extends Effect {
    public SicknessEffect() {
        super(EffectType.HARMFUL, 7033103);
        this.addAttributesModifier(Attributes.MOVEMENT_SPEED,"7107DE1E-7CE2-4040-930E-578C1F160123", -0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributesModifier(Attributes.ATTACK_DAMAGE,"7907DE9E-7CE2-9040-930E-978C1F160123", -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        Random r = new Random();
        int result = r.nextInt(1000);
        if(result == 123){
            entityLivingBaseIn.addPotionEffect(new EffectInstance(EffectInit.SEVERE_SICKNESS.get(), 99999));
            entityLivingBaseIn.removePotionEffect(EffectInit.SICKNESS.get());
        }
    }
}
