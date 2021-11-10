package com.fyp1155125212.fypmod.events;

import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.init.EffectInit;
import com.fyp1155125212.fypmod.item.custom.ModSpawnEggItem;
import com.fyp1155125212.fypmod.item.custom.complex_item_one_class;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = fypMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event){
        Entity livingEntity = event.getEntityLiving();
        if(livingEntity instanceof PlayerEntity){

            PlayerEntity player = (PlayerEntity) livingEntity;
            if(!((player).isPotionActive(EffectInit.CLEANED.get()))){
                if(Math.random()<0.5){
                    complex_item_one_class.applyEffect2(player, 99999);

                }
            }
        }

    }

}

// {
//            if(!(((PlayerEntity) entity).isPotionActive(EffectInit.CLEANED.get()))){
//
//                if(Math.random()<0.5){
//                    complex_item_one_class.applyEffect2((PlayerEntity)entity, 99999);
//
//                }
//            }
//        }