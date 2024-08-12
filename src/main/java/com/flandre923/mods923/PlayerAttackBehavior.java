package com.flandre923.mods923;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerAttackBehavior {

    public static Map<Player, AttackCounter> PlayerAttackCountMap = new HashMap<>();

    public static void onAttack(Player player, int lastTick) {
        AttackCounter attackCounter = PlayerAttackCountMap.computeIfAbsent(
                player,
                AttackCounter::new
        );

        if (attackCounter.player == player) {
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
            // qeustion
            ExampleMod.LOGGER.warn(MathQuestionRegistries.mathQuestionMap.get(1).get(0).getQuestion() );
            // answer
            ExampleMod.LOGGER.warn(MathQuestionRegistries.mathQuestionMap.get(1).get(0).getCorrectAnswer().toString() );
            //
            if (player.level().isClientSide){
                openGui(player,MathQuestionRegistries.mathQuestionMap.get(1).get(0));
            }
        }
    }

    public static boolean isTen(Player player){
        return PlayerAttackCountMap.get(player).getAttackCount() % 10 == 0;
    }

    public static void openGui(Player player,MathQuestion mathQuestion){
        Minecraft.getInstance().setScreen(new MathQuestionScreen(Component.literal("Math Question"),mathQuestion,10));
    }
}
