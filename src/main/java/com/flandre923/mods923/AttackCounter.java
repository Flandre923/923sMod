package com.flandre923.mods923;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class AttackCounter {
    private static final int ALLOW_TIMES = 20 * 3;
    public Level level;
    public Player player;
    public UUID uuid;
    public int lastTick;
    public int attackCount;
    public boolean isMutiDmage;
    public boolean isHurtPlayer;

    public AttackCounter(Player player) {
        this.player = player;
        this.level = player.level();
        this.uuid = player.getUUID();
        this.lastTick = player.tickCount;
        this.attackCount = 1;
        this.isMutiDmage = false;
        this.isHurtPlayer = false;
    }


    public void setLastTick(int lastTick) {
        this.lastTick = lastTick;
    }

    public int getLastTick() {
        return this.lastTick;
    }

    public boolean isIn20s(int lastTick){
        return lastTick - this.lastTick <= ALLOW_TIMES;
    }

    public Player getPlayer(){
        if(this.player!=null){
            return this.player;
        }else if(this.uuid!=null){
            this.player = this.level.getPlayerByUUID(this.uuid);
        }
        return null;
    }

    public int getAttackCount(){
        return this.attackCount;
    }

    public void setAttackCount(int attackCount){
        this.attackCount = Mth.clamp(attackCount, 0, 100);
    }

    public void increaseAttack() {
        this.attackCount++;
    }

    public void setIsMutiDmage(boolean isMutiDmage){
        this.isMutiDmage = isMutiDmage;
    }
    public void setIsHurtPlayer(boolean isHurtPlayer){
        this.isHurtPlayer = isHurtPlayer;
    }

    public boolean isMutiDmage(){
        return this.isMutiDmage;
    }

    public boolean isHurtPlayer(){
        return this.isHurtPlayer;
    }

    public void resetAttackCount(){
        this.attackCount = 0;
        this.setLastTick(this.player.tickCount);
    }

    public void resetFlag(){
        this.setIsMutiDmage(false);
        this.setIsHurtPlayer(false);
    }

}
