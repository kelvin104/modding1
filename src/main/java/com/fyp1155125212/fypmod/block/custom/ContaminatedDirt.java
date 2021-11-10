package com.fyp1155125212.fypmod.block.custom;




import com.fyp1155125212.fypmod.init.EffectInit;
import com.fyp1155125212.fypmod.init.ItemInit;
import net.minecraft.block.Block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundEvents;
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

            if((((PlayerEntity) entity).isPotionActive(EffectInit.VACCINATED.get()))){
                entity.playSound(SoundEvents.BLOCK_SAND_FALL,0.1F,0.1F);
            }
            else if((((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK.get())){
                if(Math.random()<0.1){ //assume full protection
                    complex_item_one_class.applyEffect2((PlayerEntity)entity, 99999);

                }

            }
            else if((((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK_HALF.get())){
                if(Math.random()<0.6){ //assume full protection
                    complex_item_one_class.applyEffect2((PlayerEntity)entity, 99999);

                }

            }
            else if((((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK_NONE.get())){
                if(Math.random()<0.9){ //assume full protection
                    complex_item_one_class.applyEffect2((PlayerEntity)entity, 99999);

                }

            }
            else{
                complex_item_one_class.applyEffect2((PlayerEntity)entity, 99999);
            }
        }
        super.onEntityWalk(world, block_pos, entity);
    }


}
