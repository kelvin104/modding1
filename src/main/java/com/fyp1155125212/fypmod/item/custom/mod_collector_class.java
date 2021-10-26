package com.fyp1155125212.fypmod.item.custom;

import com.fyp1155125212.fypmod.entity.custom.NeutralCitizen_J;
import com.fyp1155125212.fypmod.entity.custom.NeutralCitizen_N;
import com.fyp1155125212.fypmod.init.BlockInit;
import com.fyp1155125212.fypmod.init.ItemInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import net.minecraft.util.Hand;
import net.minecraft.world.World;


import java.util.Objects;

public class mod_collector_class extends Item {
    public mod_collector_class(Properties p_41383_) {
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

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack p_41398_, PlayerEntity p_41399_, LivingEntity p_41400_, Hand p_41401_) {
        if(p_41400_ instanceof NeutralCitizen_J){
            if(Math.random()<0.8){
                //player_entity.playSound(SoundEvents.BOTTLE_EMPTY,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_AMBIENT,2.0F,1.0F);
                p_41399_.inventory.deleteStack(p_41398_);

            }
            else{
                p_41399_.inventory.deleteStack(p_41398_);
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_DEATH,2.0F,1.0F);
                p_41399_.inventory.addItemStackToInventory(new ItemStack(ItemInit.FILLED_COLLECTOR.get()));
            }
        }
        if(p_41400_ instanceof NeutralCitizen_N){
            if(Math.random()<0.5){
                //player_entity.playSound(SoundEvents.BOTTLE_EMPTY,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_AMBIENT,2.0F,1.0F);
                p_41399_.inventory.deleteStack(p_41398_);

            }
            else{
                p_41399_.inventory.deleteStack(p_41398_);
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_DEATH,2.0F,1.0F);
                p_41399_.inventory.addItemStackToInventory(new ItemStack(ItemInit.FILLED_COLLECTOR.get()));
            }
        }
        return super.itemInteractionForEntity(p_41398_, p_41399_, p_41400_, p_41401_);
    }

    private void rightClick1(BlockState clicked_block, ItemUseContext context, PlayerEntity player_entity) {
        if(clicked_block.getBlock() == BlockInit.CONTAMINATED_DIRT.get()){
            if(Math.random()<0.7){
                //player_entity.playSound(SoundEvents.BOTTLE_EMPTY,2.0F,1.0F);
               // player_entity.playSound(SoundEvents.PILLAGER_AMBIENT,2.0F,1.0F);
                player_entity.inventory.deleteStack(player_entity.getHeldItemMainhand());

            }
            else{
                player_entity.inventory.deleteStack(player_entity.getHeldItemMainhand());
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
               // player_entity.playSound(SoundEvents.PILLAGER_DEATH,2.0F,1.0F);
                player_entity.inventory.addItemStackToInventory(new ItemStack(ItemInit.FILLED_COLLECTOR.get()));
            }

           // player_entity.inventory.add(new ItemStack())
        }
    }


}
