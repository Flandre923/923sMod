package com.flandre923.mods923;

import com.flandre923.mods923.mathQuestion.MathQuestion;
import com.flandre923.mods923.mathQuestion.StringMathQuestion;
import com.flandre923.mods923.network.packet.s2c.OpenScreenS2C;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerAttackBehavior {

    public static Map<UUID, AttackCounter> playerAttackCountMap = new HashMap<>();

    public static void onAttack(Player player, int lastTick) {
        AttackCounter attackCounter = playerAttackCountMap.computeIfAbsent(
                player.getUUID(),
                (uuid -> new AttackCounter(player))
        );
        if (attackCounter.uuid == player.getUUID() ) {
            if (attackCounter.isIn20s(lastTick)) {
                attackCounter.increaseAttack();
                attackCounter.setLastTick(lastTick);
            } else {
                attackCounter.setAttackCount(0);
                attackCounter.setLastTick(lastTick);
            }

            postAttackCounter(player);
        }
    }

    public static void postAttackCounter(Player player){
        if(isTen(player)){
            if (!player.level().isClientSide){
//                openGui(player,MathQuestionRegistries.mathQuestionMap.get(1).get(0));
                PacketDistributor.sendToPlayer((ServerPlayer) player,new OpenScreenS2C(1,1));
            }

        }
    }

    public static boolean isTen(Player player){
        return playerAttackCountMap.get(player.getUUID()).getAttackCount() % 10 == 0;
    }

    public static boolean isPlayerMutiDamage(Player player){
        return playerAttackCountMap.get(player.getUUID()).isMutiDmage();
    }

    public static float getAttackMuti(){
        return 20f;
    }

    public static boolean isPlayerHurt(Player player){
        return playerAttackCountMap.get(player.getUUID()).isHurtPlayer();
    }

    public static float getHurtDamage(){
        return 2f;
    }

    public static void openGui(int difficulty,int mathQuestionID){
        MathQuestion<?> mathQuestion = MathQuestionRegistries.mathQuestionMap.get(difficulty).get(mathQuestionID);
        Minecraft.getInstance().setScreen(new MathQuestionScreen(Component.literal("Math Question"),mathQuestion,10));
    }

    public static void setHurtFlag(UUID uuid, int id){
        Player player = getPlayer(uuid, id);
        if (player != null){
            playerAttackCountMap.get(player.getUUID()).setIsHurtPlayer(true);
        }
    }

    public static void setAttackMutiDamageFlag(UUID uuid,int id){
        Player player = getPlayer(uuid, id);
        if (player != null){
            playerAttackCountMap.get(player.getUUID()).setIsMutiDmage(true);
        }
    }

    public static Player getPlayer(UUID uuid,int id){
        for (AttackCounter attackCounter : playerAttackCountMap.values()) {
            if ( attackCounter.uuid.equals(uuid)){
                return attackCounter.player;
            }
        }
        return null;
    }


    public static void resetFlag(Player player){
        playerAttackCountMap.get(player.getUUID()).resetFlag();
    }

}
