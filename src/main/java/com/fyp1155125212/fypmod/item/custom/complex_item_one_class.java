package com.fyp1155125212.fypmod.item.custom;


import com.fyp1155125212.fypmod.entity.custom.CoughEntity;
import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;

import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;

public class complex_item_one_class extends Item {
    public complex_item_one_class(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote){
            PlayerEntity player_entity = Objects.requireNonNull(context.getPlayer());
            BlockState clicked_block = world.getBlockState(context.getPos());
            rightClick1(clicked_block, context, player_entity);
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClick1(BlockState clicked_block, ItemUseContext context, PlayerEntity player_entity) {
        player_entity.addPotionEffect(new EffectInstance(EffectInit.SICKNESS.get(), 100));
    }



    ////for potion effect
    public static void applyEffect2(PlayerEntity player_entity) {
        player_entity.addPotionEffect(new EffectInstance(EffectInit.SICKNESS.get(), 100));
    }


    ////for entity effect
    //public static void applyEffect1(Entity entity, int second){
  //      entity.setSecondsOnFire(second);
 //   }
}
