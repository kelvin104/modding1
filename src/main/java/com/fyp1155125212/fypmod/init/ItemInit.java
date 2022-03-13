package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.ModTab;
import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.item.ModItemTier;
import com.fyp1155125212.fypmod.item.custom.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, fypMod.MOD_ID);


    public static final RegistryObject<Item> MEDICAL_METAL = ITEMS.register("medical_metal",
            () -> new Item(new Item.Properties().group(ModTab.MODDED_ITEMS)));


    public static final RegistryObject<Item> COLLECTOR = ITEMS.register("collector",
            () -> new mod_collector_class(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> FILLED_COLLECTOR = ITEMS.register("filled_collector",
            () -> new mod_collector_filled_class(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> VACCINE_100 = ITEMS.register("vaccine_100",
            () -> new Vaccine_100(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> BIONTECH = ITEMS.register("biontech",
            () -> new BioNTech(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> SINOVAC = ITEMS.register("sinovac",
            () -> new SinoVac(new Item.Properties().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> HANDRUB = ITEMS.register("handrub",
            () -> new HandRub(new Item.Properties().group(ModTab.MODDED_ITEMS)));

    public static final RegistryObject<Item> BATON = ITEMS.register("baton",
            () -> new mod_weapon_class(ModItemTier.ITEM_ONE, 0,5f,new Item.Properties().group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> MASK = ITEMS.register("mask",
            () -> new MaskFull(new Item.Properties().setNoRepair().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> MASK_HALF = ITEMS.register("mask_half",
            () -> new MaskHalf(new Item.Properties().setNoRepair().maxStackSize(1).group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<Item> MASK_NONE = ITEMS.register("mask_none",
            () -> new MaskNone(new Item.Properties().setNoRepair().maxStackSize(1).group(ModTab.MODDED_ITEMS)));


    public static final RegistryObject<ModSpawnEggItem> POLICE_SPAWN_EGG = ITEMS.register("police_spawn_egg",
            () -> new ModSpawnEggItem(EntityTypesInit.POLICE, 0x000000, 0x630000,
                    new Item.Properties().group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<ModSpawnEggItem> NEUTRAL_CITIZEN_SPAWN_EGG = ITEMS.register("neutral_citizen_spawn_egg",
            () -> new ModSpawnEggItem(EntityTypesInit.NEUTRAL_CITIZEN, 0xFFFFBF, 0x63ADBC,
                    new Item.Properties().group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<ModSpawnEggItem> NEUTRAL_CITIZEN_J_SPAWN_EGG = ITEMS.register("neutral_citizen_j_spawn_egg",
            () -> new ModSpawnEggItem(EntityTypesInit.NEUTRAL_CITIZEN_J, 0xFFFFBF, 0x63AD32,
                    new Item.Properties().group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<ModSpawnEggItem> NEUTRAL_CITIZEN_N_SPAWN_EGG = ITEMS.register("neutral_citizen_n_spawn_egg",
            () -> new ModSpawnEggItem(EntityTypesInit.NEUTRAL_CITIZEN_N, 0xFFFFBF, 0xCF7232,
                    new Item.Properties().group(ModTab.MODDED_ITEMS)));
    public static final RegistryObject<ModSpawnEggItem> DOCTOR_SPAWN_EGG = ITEMS.register("doctor_spawn_egg",
            () -> new ModSpawnEggItem(EntityTypesInit.DOCTOR, 0xFFFFFF, 0x000000,
                    new Item.Properties().group(ModTab.MODDED_ITEMS)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}