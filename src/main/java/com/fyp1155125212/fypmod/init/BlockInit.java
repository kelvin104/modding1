package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.ModTab;
import com.fyp1155125212.fypmod.block.custom.ContaminatedDirt;
import com.fyp1155125212.fypmod.fypMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, fypMod.MOD_ID);

    public static final RegistryObject<Block> MEDICAL_METAL_ORE = registerBlock("medical_metal_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(3f)));


    public static final RegistryObject<Block> CONTAMINATED_DIRT = registerBlock("contaminated_dirt",
            () -> new ContaminatedDirt(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND)
                    .harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5f)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModTab.MODDED_ITEMS)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
