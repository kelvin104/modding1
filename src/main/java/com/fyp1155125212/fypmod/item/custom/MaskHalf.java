package com.fyp1155125212.fypmod.item.custom;

import com.fyp1155125212.fypmod.init.ItemInit;
import com.fyp1155125212.fypmod.item.ModArmorMaterial;
import com.fyp1155125212.fypmod.item.ModArmorMaterialHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MaskHalf extends MaskItem{
    public MaskHalf(Properties p_i48534_3_) {
        super(ModArmorMaterialHalf.HALF, EquipmentSlotType.HEAD, p_i48534_3_);
    }



    @Override
    public void inventoryTick(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if (!p_77663_2_.isRemote) {
            PlayerEntity playerEntity = (PlayerEntity)(p_77663_3_);

            if(playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()== ItemInit.MASK_HALF.get()){

                playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).damageItem(1,playerEntity, (entity) -> {

                    entity.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ItemInit.MASK_NONE.get()));
                });
            }
        }
    }
}
