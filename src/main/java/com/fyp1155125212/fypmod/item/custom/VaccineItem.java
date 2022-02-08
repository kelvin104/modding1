package com.fyp1155125212.fypmod.item.custom;

import com.fyp1155125212.fypmod.init.EffectInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

import java.util.Objects;


public abstract class VaccineItem extends Item {
    public VaccineItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }


    public abstract void vaccinatePlayer(PlayerEntity player, int time);
}