package com.fyp1155125212.fypmod.item.custom;

import com.fyp1155125212.fypmod.item.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;

public class MaskItem extends ArmorItem {
    public MaskItem(Properties p_i48534_3_) {
        super(ModArmorMaterial.RUBBER, EquipmentSlotType.HEAD, p_i48534_3_);
    }

    @Override
    public int getDamageReduceAmount() {
        return 0;
    }
}
