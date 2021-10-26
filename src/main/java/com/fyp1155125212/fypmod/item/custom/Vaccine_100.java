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

public class Vaccine_100 extends Item {
    public Vaccine_100(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote){
            PlayerEntity player_entity = Objects.requireNonNull(context.getPlayer());
            BlockState clicked_block = world.getBlockState(context.getPos());
            vaccinatePlayer(player_entity);
        }
        return super.onItemUseFirst(stack, context);
    }
    public void vaccinatePlayer(PlayerEntity player){
        player.addPotionEffect(new EffectInstance(EffectInit.VACCINATED.get(), 999999));
    }
}
