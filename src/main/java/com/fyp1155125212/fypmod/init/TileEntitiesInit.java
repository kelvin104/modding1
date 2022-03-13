package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.tileentity.VaccineMakerTile;
import net.minecraftforge.eventbus.api.IEventBus;
import com.fyp1155125212.fypmod.fypMod;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntitiesInit {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, fypMod.MOD_ID);

    public static RegistryObject<TileEntityType<VaccineMakerTile>> VACCINE_MAKER_TILE =
            TILE_ENTITIES.register("vaccine_maker_tile", () -> TileEntityType.Builder.create(
                    VaccineMakerTile::new, BlockInit.VACCINE_MAKER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
