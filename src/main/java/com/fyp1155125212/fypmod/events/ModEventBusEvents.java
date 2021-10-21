package com.fyp1155125212.fypmod.events;

import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.item.custom.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = fypMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}