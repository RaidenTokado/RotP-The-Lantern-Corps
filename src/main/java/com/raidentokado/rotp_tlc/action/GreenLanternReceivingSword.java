package com.raidentokado.rotp_tlc.action;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.raidentokado.rotp_tlc.init.InitItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GreenLanternReceivingSword extends StandEntityAction {

    public GreenLanternReceivingSword(StandEntityAction.Builder builder) {
        super(builder);
    }
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
        if(!world.isClientSide()){
            if(userPower.getUser() instanceof PlayerEntity){
                ItemStack itemstack = new ItemStack(InitItems.GLSwordItem.get(), 1);
                PlayerEntity player = (PlayerEntity) userPower.getUser();
                player.addItem(itemstack);
            }
        }
    }
    @Override
    public boolean enabledInHudDefault() {
        return false;
    }

}