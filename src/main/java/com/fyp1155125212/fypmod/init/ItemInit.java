package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.ModTab;
import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.item.ModItemTier;
import com.fyp1155125212.fypmod.item.custom.complex_item_one_class;
import com.fyp1155125212.fypmod.item.custom.mod_collector_class;
import com.fyp1155125212.fypmod.item.custom.mod_collector_filled_class;
import com.fyp1155125212.fypmod.item.custom.mod_weapon_class;
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

    public static final RegistryObject<Item> COMPLEX_ITEM_ONE = ITEMS.register("complex_item_one",
            () -> new complex_item_one_class(new Item.Properties().group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> COLLECTOR = ITEMS.register("collector",
            () -> new mod_collector_class(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> FILLED_COLLECTOR = ITEMS.register("filled_collector",
            () -> new mod_collector_filled_class(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> BATON = ITEMS.register("baton",
            () -> new mod_weapon_class(ModItemTier.ITEM_ONE, 0,5f,new Item.Properties().group(ModTab.MODDED_ITEMS)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}