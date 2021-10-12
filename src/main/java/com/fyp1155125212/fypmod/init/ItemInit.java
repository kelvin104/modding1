package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.ModTab;
import com.fyp1155125212.fypmod.fypMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, fypMod.MOD_ID);


    public static final RegistryObject<Item> ITEM_ONE = ITEMS.register("item_one",
            () -> new Item(new Item.Properties().group(ModTab.MODDED_ITEMS)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}