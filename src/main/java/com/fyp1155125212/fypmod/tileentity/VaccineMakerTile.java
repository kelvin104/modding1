package com.fyp1155125212.fypmod.tileentity;

import com.fyp1155125212.fypmod.init.ItemInit;
import com.fyp1155125212.fypmod.init.TileEntitiesInit;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VaccineMakerTile extends TileEntity {
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public VaccineMakerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public VaccineMakerTile() {
        this(TileEntitiesInit.VACCINE_MAKER_TILE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0: return stack.getItem() == ItemInit.VACCINE_100.get();
                    case 1:
                    case 2:
                    case 3:
                        return stack.getItem() == ItemInit.FILLED_COLLECTOR.get() ||
                                stack.getItem() == ItemInit.COLLECTOR.get();
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void lightningHasStruck() {
        boolean hasFocusInFirstSlot = this.itemHandler.getStackInSlot(1).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.GLASS_PANE;
        boolean hasSampleInSecondSlot = this.itemHandler.getStackInSlot(2).getCount() > 0
                && this.itemHandler.getStackInSlot(1).getItem() == ItemInit.VACCINE_100.get();

        if(hasFocusInFirstSlot && hasSampleInSecondSlot) {
            this.itemHandler.getStackInSlot(1).shrink(1);
            this.itemHandler.getStackInSlot(2).shrink(1);

            this.itemHandler.insertItem(0, new ItemStack(ItemInit.VACCINE_100.get()), false);
        }
    }
}
