package com.raidentokado.rotp_tlc.init;

import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.block.LanternBox;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RotpTLCAddon.MOD_ID);
    public static final RegistryObject<LanternBox> LANTERN_BOX = BLOCKS.register("lantern_box",
            () -> new LanternBox(Block.Properties.of(Material.METAL).strength(10.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops().sound(SoundType.METAL)));
}
