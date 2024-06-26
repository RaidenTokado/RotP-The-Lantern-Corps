package com.raidentokado.rotp_tlc.action;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class GreenLanternUnreceiving extends StandEntityAction {

    public GreenLanternUnreceiving(StandEntityAction.Builder builder) {
        super(builder);
    }
    public static ItemStack GLItemUnrec (@NotNull PlayerEntity player){
        ItemStack itemStack = ItemStack.EMPTY;
        for(int i=0;i<player.inventory.getContainerSize();i++){
            ItemStack item = player.inventory.getItem(i);
            Item type = item.getItem();
            if(!item.isEmpty()){
                if(type == Items.SNOWBALL){
                    itemStack= item;
                    itemStack.shrink(itemStack.getCount());
                }
            }
        }
        return itemStack;
    }
    @Override
    public void standPerform(@NotNull World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
        if(!world.isClientSide){
            if(power.getUser() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) power.getUser();
                for(int i=0;i<player.inventory.getContainerSize();i++){
                    ItemStack item = player.inventory.getItem(i);
                    Item type = item.getItem();
                    if(!item.isEmpty()){
                        if(type == Items.SNOWBALL){
                            item.shrink(item.getCount());
                        }

                    }
                }
            }
        }
    }
    @Override
    public boolean enabledInHudDefault() {
        return false;
    }

}