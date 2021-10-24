package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.entity.custom.*;
import com.fyp1155125212.fypmod.fypMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypesInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, fypMod.MOD_ID);

    public static final RegistryObject<EntityType<Police>> POLICE= ENTITY_TYPES.register("police",
            () -> EntityType.Builder.create(Police::new, EntityClassification.MONSTER).size(1.0f,1.0f).build(new ResourceLocation(fypMod.MOD_ID,"police").toString()));

    public static final RegistryObject<EntityType<NeutralCitizen>> NEUTRAL_CITIZEN = ENTITY_TYPES.register("neutral_citizen",
            () -> EntityType.Builder.create(NeutralCitizen::new, EntityClassification.MONSTER).size(1.0f,1.0f).build(new ResourceLocation(fypMod.MOD_ID,"neutral_citizen").toString()));

    public static final RegistryObject<EntityType<NeutralCitizen_J>> NEUTRAL_CITIZEN_J = ENTITY_TYPES.register("neutral_citizen_j",
            () -> EntityType.Builder.create(NeutralCitizen_J::new, EntityClassification.MONSTER).size(1.0f,1.0f).build(new ResourceLocation(fypMod.MOD_ID,"neutral_citizen_j").toString()));

    public static final RegistryObject<EntityType<NeutralCitizen_N>> NEUTRAL_CITIZEN_N = ENTITY_TYPES.register("neutral_citizen_n",
            () -> EntityType.Builder.create(NeutralCitizen_N::new, EntityClassification.MONSTER).size(1.0f,1.0f).build(new ResourceLocation(fypMod.MOD_ID,"neutral_citizen_n").toString()));

    public static final RegistryObject<EntityType<CoughEntity>> COUGH = ENTITY_TYPES.register("cough",
            () -> EntityType.Builder.<CoughEntity>create(CoughEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).trackingRange(4).updateInterval(10).build(new ResourceLocation(fypMod.MOD_ID,"cough").toString()));



}
