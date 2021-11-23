package com.fyp1155125212.fypmod.world.biome;

import com.fyp1155125212.fypmod.fypMod;
import com.fyp1155125212.fypmod.init.EntityTypesInit;
import com.fyp1155125212.fypmod.world.gen.structure.ModStructureBuilder;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.datafix.fixes.StructureReferenceFix;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
;

import java.util.function.Supplier;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, fypMod.MOD_ID);

    public static final RegistryObject<Biome> POLLUTED_BIOME = BIOMES.register("polluted_biome",
            () -> makePollutedBiome(() -> ModConfiguredSurfaceBuilders.POLLUTED_SURFACE, 0.125f, 0.05f));

    private static Biome makePollutedBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        DefaultBiomeFeatures.withPassiveMobs(mobspawninfo$builder);
        DefaultBiomeFeatures.withBatsAndHostiles(mobspawninfo$builder);
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityTypesInit.NEUTRAL_CITIZEN.get(), 50, 6, 6));
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityTypesInit.NEUTRAL_CITIZEN_J.get(), 50, 1, 2));
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityTypesInit.NEUTRAL_CITIZEN_N.get(), 50, 0, 1));
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityTypesInit.POLICE.get(), 100, 1, 1));
        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);




        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);

        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withClayDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);

        biomegenerationsettings$builder.withStructure(ModStructureBuilder.ModStructureFeatures.MOD_HOUSE);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(12162386).setWaterFogColor(12371337)
                        .setFogColor(2828837).withSkyColor(getSkyColorWithTemperatureModifier(0.8F)).withFoliageColor(2828837).withGrassColor(599306)
                        .setParticle(new ParticleEffectAmbience(ParticleTypes.ASH, 0.05f)).withSkyColor(5460819)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
                        .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST))
                        .build())
                .withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}

