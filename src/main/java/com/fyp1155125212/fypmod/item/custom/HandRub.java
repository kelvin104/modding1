package com.fyp1155125212.fypmod.item.custom;

import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class HandRub extends Item {
    public HandRub(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }
/*
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote){
            PlayerEntity player_entity = Objects.requireNonNull(context.getPlayer());
            BlockState clicked_block = world.getBlockState(context.getPos());
            player_entity.addPotionEffect(new EffectInstance(EffectInit.CLEANED.get(), 200));
            if(!player_entity.isCreative()){
                stack.shrink(1);
            }
        }
        return super.onItemUseFirst(stack, context);
    }
*/
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity p_77659_2_, Hand p_77659_3_) {

        if (!world.isRemote){
            PlayerEntity player_entity = Objects.requireNonNull(p_77659_2_);
            player_entity.addPotionEffect(new EffectInstance(EffectInit.CLEANED.get(), 200));
            if(!player_entity.isCreative()){
                p_77659_2_.getHeldItem(p_77659_3_).shrink(1);
            }
        }
        return super.onItemRightClick(world, p_77659_2_, p_77659_3_);
    }


}
