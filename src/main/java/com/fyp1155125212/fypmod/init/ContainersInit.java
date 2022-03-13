package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.container.VaccineMakerContainer;
import com.fyp1155125212.fypmod.fypMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainersInit {
    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, fypMod.MOD_ID);

    public static final RegistryObject<ContainerType<VaccineMakerContainer>> VACCINE_MAKER_CONTAINER
            = CONTAINERS.register("vaccine_maker_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new VaccineMakerContainer(windowId, world, pos, inv, inv.player);
            })));


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
