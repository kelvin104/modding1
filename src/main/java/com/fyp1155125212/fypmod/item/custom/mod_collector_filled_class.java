package com.fyp1155125212.fypmod.item.custom;

import com.fyp1155125212.fypmod.init.ItemInit;
import com.mod1.try1.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.world.world;
import net.minecraft.world.world.block.Blocks;
import net.minecraft.world.world.block.state.BlockState;

import java.util.Objects;

public class mod_collector_filled_class extends Item {
    public mod_collector_filled_class(Properties p_41383_) {
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
        if(clicked_block.getBlock() == Blocks.GRASS_BLOCK){

                complex_item_one_class.applyEffect2(player_entity);
                player_entity.getInventory().removeItem(context.getItemInHand());
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
                player_entity.getInventory().add(new ItemStack(ItemInit.MOD_COLLECTOR.get()));


            // player_entity.getInventory().add(new ItemStack())
        }
    }

}
