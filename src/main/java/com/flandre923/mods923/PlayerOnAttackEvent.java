package com.flandre923.mods923;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class PlayerOnAttackEvent {
    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event){
        Player player = event.getEntity();
        PlayerAttackBehavior.onAttack(player,player.tickCount);
        ExampleMod.LOGGER.warn( "PlayerOnAttackEvent player attack count:" + PlayerAttackBehavior.PlayerAttackCountMap.get(player).getAttackCount());
    }
}
