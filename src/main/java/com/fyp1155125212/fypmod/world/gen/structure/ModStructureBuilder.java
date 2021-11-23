package com.fyp1155125212.fypmod.world.gen.structure;

import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.init.StructuresInit;
import com.fyp1155125212.fypmod.world.gen.structure.structures.HouseStructure;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraftforge.common.extensions.IForgeStructure;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/*public class ModStructureBuilder {
    public abstract static class ModStructure<C extends IFeatureConfig> extends net.minecraftforge.registries.ForgeRegistryEntry<net.minecraft.world.gen.feature.structure.Structure<?>> implements net.minecraftforge.common.extensions.IForgeStructure{
        public static final BiMap<String, Structure<?>> NAME_STRUCTURE_BIMAP = HashBiMap.create();
        private static final Map<Structure<?>, GenerationStage.Decoration> STRUCTURE_DECORATION_STAGE_MAP = Maps.newHashMap();
        public static final Structure<NoFeatureConfig> HOUSE = Registry.register("House", new HouseStructure(), GenerationStage.Decoration.SURFACE_STRUCTURES);
        private static <F extends net.minecraft.world.gen.feature.structure.Structure<?>> F register(String name, F structure, GenerationStage.Decoration decorationStage) {
            NAME_STRUCTURE_BIMAP.put(name.toLowerCase(Locale.ROOT), structure);
            STRUCTURE_DECORATION_STAGE_MAP.put(structure, decorationStage);
            return Registry.register(Registry.STRUCTURE_FEATURE, name.toLowerCase(Locale.ROOT), structure);
        }

    }
    public static class ModStructureFeatures{
        public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MOD_HOUSE = register("mod_house", ModStructure.HOUSE.withConfiguration(NoFeatureConfig.INSTANCE));

        private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(String name, StructureFeature<FC, F> structure) {
            return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structure);
        }
    }



    //public static void withModStructures(BiomeGenerationSettings.Builder builder) {
    //    biomegenerationsettings$builder.withStructure(ModStructureFeatures.MOD_HOUSE);
   // }
}
*/
