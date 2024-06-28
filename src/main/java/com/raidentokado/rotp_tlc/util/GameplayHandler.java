package com.raidentokado.rotp_tlc.util;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.raidentokado.rotp_tlc.RotpTLCAddon;
import com.raidentokado.rotp_tlc.init.InitItems;
import com.raidentokado.rotp_tlc.init.InitStands;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

import static com.raidentokado.rotp_tlc.init.InitItems.GLSwordItem;


@Mod.EventBusSubscriber(modid = RotpTLCAddon.MOD_ID)
public class GameplayHandler {

    static boolean once = false;
    static double dmg;
    static double dig;


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onEntityTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;



        IStandPower.getStandPowerOptional(player).ifPresent(power -> {
            StandType<?> wa = InitStands.STAND_GREEN_LANTERN.getStandType();
            /**/
            if(power.getType() == wa ){
                if(power.getStandManifestation() instanceof StandEntity){
                    List<LivingEntity> list = player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(11),EntityPredicates.ENTITY_STILL_ALIVE);
                    list.forEach(entity -> {
                        if(entity.distanceTo(player) <=10 && (entity != player)){
                            int amount = Math.round(2-entity.distanceTo(player)/5);
                        }
                    });
                    Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(15);


                }else {
                    Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(0);
                }

            }else {
                Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(0);
            }
            if(power.getHeldAction()==InitStands.GREEN_LANTERN_BLOCK.get()){
                List<LivingEntity> list = player.level.getEntitiesOfClass(LivingEntity.class,player.getBoundingBox().inflate(11),EntityPredicates.ENTITY_STILL_ALIVE);
                list.forEach(entity -> {
                    if(entity.distanceTo(player) <=10 && (entity != player)){
                        int amount = Math.round(4-2*entity.distanceTo(player)/5);
                    }
                });
                if(power.getStamina() ==0){
                    power.stopHeldAction(false);
                }
            }

        });

    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        if(!player.level.isClientSide()){
            IStandPower.getStandPowerOptional(player).ifPresent(standPower -> {
                StandType<?> cs = InitStands.STAND_GREEN_LANTERN.getStandType();
                if(standPower.getType() == cs){
                    if(standPower.getStandManifestation() instanceof StandEntity) {
                        if(!hasPlayerCS(player) && !isItemCS(player)){
                            ItemStack itemStack = new ItemStack(GLSwordItem.get(),1);
                            CompoundNBT nbt = new CompoundNBT();
                            itemStack.setTag(nbt);

                            nbt.putString("owner",player.getName().getString());


                            player.addItem(itemStack);

                        }else {
                            if(hasPlayerCS(player)){
                                clearEntCS(player);
                            }
                            delDupCS(player);
                        }


                    }else {
                        clearCS(player);
                        clearEntCS(player);
                    }
                }else{
                    clearCS(player);
                    clearEntCS(player);
                }
            });
        }
    }




    private static void clearCS(PlayerEntity players){
        if(players instanceof ServerPlayerEntity){
            ServerPlayerEntity servPlater =  (ServerPlayerEntity) players;
            servPlater.getLevel().players().forEach(player -> {
                for (int i=0; i<player.inventory.getContainerSize();++i){
                    if(csOwner(players,player.inventory.getItem(i))){
                        player.inventory.getItem(i).shrink(1);
                    }
                }
            });
        }else {
            for (int i=0; i<players.inventory.getContainerSize();++i){
                if(csOwner(players,players.inventory.getItem(i))){
                    players.inventory.getItem(i).shrink(1);
                }
            }
        }
    }

    private static void clearEntCS(PlayerEntity player){
        if(player instanceof ServerPlayerEntity){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            serverPlayer.getLevel().getEntities().filter(entity -> entity instanceof ItemEntity && ((ItemEntity)entity).getItem().getItem() == GLSwordItem.get())
                    .forEach(entity -> {
                        ItemStack itemStack = ((ItemEntity) entity).getItem();
                        if(csOwner(player,itemStack)){
                            entity.remove();
                        }
                    });
        }
    }


    private static void delDupCS(PlayerEntity players){
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) players;
        int count = 0;
        for (ServerPlayerEntity player : serverPlayer.getLevel().players()) {
            for (int i=0; i<player.inventory.getContainerSize();++i){
                ItemStack itemStack = player.inventory.getItem(i);
                if(csOwner(players,itemStack)){
                    count++;
                    if(count>1){
                        itemStack.shrink(1);
                    }
                }
            }
        }
    }


    private static boolean hasPlayerCS(PlayerEntity players){
        if(players instanceof ServerPlayerEntity){
            boolean turn = false;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) players;
            for (ServerPlayerEntity player : serverPlayer.getLevel().players()) {
                for(int i=0;i<player.inventory.getContainerSize();++i){
                    if(csOwner(players, player.inventory.getItem(i))){
                        turn = true;
                        i= player.inventory.getContainerSize();
                    }
                }
            }
            return turn;
        }else {
            boolean turn = false;
            for(int i=0;i<players.inventory.getContainerSize();++i){
                turn =csOwner(players, players.inventory.getItem(i));
                i= players.inventory.getContainerSize();
            }
            return turn;
        }
    }

    private static boolean isItemCS(PlayerEntity players){
        if(players instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) players;
            boolean turn = player.getLevel().getEntities().filter(entity -> entity instanceof ItemEntity && ((ItemEntity)entity).getItem().getItem() == InitItems.GLSwordItem.get())
                    .anyMatch(entity -> {
                        ItemStack itemStack = ((ItemEntity) entity).getItem();
                        return csOwner(player, itemStack);
                    });

            return turn;
        }else {
            return players.level.getEntitiesOfClass(ItemEntity.class,players.getBoundingBox().inflate(1000), EntityPredicates.ENTITY_STILL_ALIVE).stream()
                    .anyMatch(itemEntity -> csOwner(players,itemEntity.getItem()));
        }
    }

    private static boolean csOwner(PlayerEntity player, ItemStack itemStack){
        if(itemStack.getItem() == InitItems.GLSwordItem.get()){
            CompoundNBT nbt = itemStack.getTag();
            if(nbt != null && nbt.contains("owner")){
                return nbt.getString("owner").equals(player.getName().getString());
            }
            return false;
        }
        return false;
    }
}