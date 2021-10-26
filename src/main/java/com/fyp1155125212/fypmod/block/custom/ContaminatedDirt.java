package com.fyp1155125212.fypmod.block.custom;




import net.minecraft.block.Block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.World;
import com.fyp1155125212.fypmod.item.custom.complex_item_one_class;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ContaminatedDirt extends Block {
    public ContaminatedDirt(Properties properties) {
        super(properties);
    }



    @Override
    @ParametersAreNonnullByDefault
    public void onEntityWalk(World world, BlockPos block_pos, Entity entity) {
        // complex_item_one_class.applyEffect1(entity,5);
        if (entity instanceof PlayerEntity){

            complex_item_one_class.applyEffect2((PlayerEntity)entity, 100);
        }
        super.onEntityWalk(world, block_pos, entity);
    }


}
