package com.raidentokado.rotp_tlc.action;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
    public boolean enabledInHudDefault() {
        return false;
    }

}