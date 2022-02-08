package com.fyp1155125212.fypmod;

import com.fyp1155125212.fypmod.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTab {

    public static final ItemGroup MODDED_ITEMS = new ItemGroup("ModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.VACCINE_100.get());
        }
    };

}