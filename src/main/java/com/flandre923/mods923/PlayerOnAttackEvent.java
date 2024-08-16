package com.flandre923.mods923;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class PlayerOnAttackEvent {
    @SubscribeEvent
    public static void onPlayerAttack(LivingDamageEvent.Pre event){
        if (event.getSource().getEntity() instanceof Player player){
            PlayerAttackBehavior.onAttack(player,player.tickCount);
            ExampleMod.LOGGER.debug( "PlayerOnAttackEvent player attack count:" + PlayerAttackBehavior.playerAttackCountMap.get(player.getUUID()).getAttackCount());
            if (PlayerAttackBehavior.isPlayerMutiDamage(player)){
                event.setNewDamage(event.getOriginalDamage() * PlayerAttackBehavior.getAttackMuti());
                PlayerAttackBehavior.resetFlag(player);
            }else if (PlayerAttackBehavior.isPlayerHurt(player)){
                //todo math hurt
                player.getAbilities().invulnerable = false;
                player.hurt(player.damageSources().source(DamageTypes.MAGIC),PlayerAttackBehavior.getHurtDamage());
                PlayerAttackBehavior.resetFlag(player);
            }
        }
    }
}
