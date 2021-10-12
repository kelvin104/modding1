package com.fyp1155125212.fypmod.item.custom;

import com.mod1.try1.effect.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.World;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

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
        //applyEffect1(player_entity,5);
       // player_entity.addEffect(new MobEffectInstance(ModEffects.SICKNESS.get(), 100),player_entity);


    }



    ////for potion effect
    public static void applyEffect2(PlayerEntity player_entity) {
      //  player_entity.addEffect(new MobEffectInstance(ModEffects.SICKNESS.get(), 100),player_entity);
    }

    ////for entity effect
    //public static void applyEffect1(Entity entity, int second){
  //      entity.setSecondsOnFire(second);
 //   }
}
