package com.raidentokado.rotp_tlc.init;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.item.GLSwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RotpTLCAddon.MOD_ID);


    public static final RegistryObject<GLSwordItem> GLSwordItem = ITEMS.register("gl_sword",
            () -> new GLSwordItem(ModItemTier.GREEN_RING_POWER, 3, -2.4F, (new Item.Properties().stacksTo(1).rarity(Rarity.RARE))));

}