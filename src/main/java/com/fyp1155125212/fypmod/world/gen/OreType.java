package com.fyp1155125212.fypmod.world.gen;

import com.fyp1155125212.fypmod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {

    ITEM_ONE_ORE(Lazy.of(BlockInit.MEDICAL_METAL_ORE), 8, 30, 80);

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static com.fyp1155125212.fypmod.world.gen.OreType get(Block block) {
        for (com.fyp1155125212.fypmod.world.gen.OreType ore : values()) {
            if(block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}