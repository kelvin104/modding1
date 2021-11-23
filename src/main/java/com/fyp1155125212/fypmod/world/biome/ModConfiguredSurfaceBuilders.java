package com.fyp1155125212.fypmod.world.biome;


import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilders {
    public static ConfiguredSurfaceBuilder<?> POLLUTED_SURFACE = register("polluted_surface",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    Blocks.COARSE_DIRT.getDefaultState(),
                    Blocks.DIRT.getDefaultState(),
                    Blocks.GRASS.getDefaultState(),
                    BlockInit.CONTAMINATED_DIRT.get().getDefaultState()
            )));

    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name,
                                                                                           ConfiguredSurfaceBuilder<SC> csb) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(fypMod.MOD_ID, name), csb);
    }
}

