package com.fyp1155125212.fypmod.init;

import com.fyp1155125212.fypmod.effect.custom.*;
import com.fyp1155125212.fypmod.fypMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, fypMod.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, fypMod.MOD_ID);

    public static final RegistryObject<Effect> SICKNESS = EFFECTS.register("sickness", SicknessEffect::new);

    public static final RegistryObject<Effect> VACCINATED = EFFECTS.register("vaccinated", VaccinatedEffect::new);

    public static final RegistryObject<Effect> CLEANED = EFFECTS.register("cleaned", Cleaned::new);

    public static final RegistryObject<Effect> VIRUS_CARRIER = EFFECTS.register("virus_carrier", VirusCarrierEffect::new);

    public static final RegistryObject<Effect> SEVERE_SICKNESS = EFFECTS.register("severe_sickness", SevereSicknessEffect::new);


    /*
    public static final RegistryObject<Potion> SICKNESS_POTION = POTIONS.register("sickness",
            () -> new Potion(new EffectInstance(SICKNESS.get(),1200,0)));
    public static final RegistryObject<Potion> LONG_SICKNESS_POTION = POTIONS.register("long_sickness",
            () -> new Potion(new EffectInstance(SICKNESS.get(),2400,0)));
    public static final RegistryObject<Potion> STRONG_SICKNESS_POTION = POTIONS.register("strong_sickness",
            () -> new Potion(new EffectInstance(SICKNESS.get(),1200,2)));

    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipes(Potions.AWKWARD,ItemInit.FILLED_COLLECTOR.get(),SICKNESS_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipes(SICKNESS_POTION.get(), Items.GLOWSTONE_DUST, STRONG_SICKNESS_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipes(SICKNESS_POTION.get(),Items.REDSTONE, LONG_SICKNESS_POTION.get()));


    }

    private static class NewBrewingRecipes implements IBrewingRecipe {
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;
        public NewBrewingRecipes(Potion bottleInput, Item itemInput, Potion outputIn){
            this.bottleInput=bottleInput;
            this.itemInput=itemInput;
            this.output = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),outputIn);
        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotionFromItem(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if(isInput(input)&&isIngredient(ingredient)){
                return this.output.copy();
            }else{
                return ItemStack.EMPTY;
            }
        }
    }
    */
}
