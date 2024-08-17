package com.flandre923.mods923.network.packet.s2c;

import com.flandre923.mods923.ExampleMod;
import com.flandre923.mods923.attack_counter.PlayerAttackBehavior;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenScreenS2C implements CustomPacketPayload {
    int mathQuestionID;
    int difficulty;

    public static final StreamCodec<FriendlyByteBuf, OpenScreenS2C> STREAM_CODEC =
            CustomPacketPayload.codec(OpenScreenS2C::write, OpenScreenS2C::new);

    public OpenScreenS2C(FriendlyByteBuf friendlyByteBuf) {
        this.mathQuestionID = friendlyByteBuf.readInt();
        this.difficulty = friendlyByteBuf.readInt();
    }

    private void write(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(mathQuestionID);
        friendlyByteBuf.writeInt(difficulty);
    }

    public OpenScreenS2C(int mathQuestionID,int difficulty) {
        this.mathQuestionID = mathQuestionID;
        this.difficulty = difficulty;
    }

    public static Type<OpenScreenS2C> TYPE =
            new Type<OpenScreenS2C>(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"open_screen_s2c"));

    // client
    public static void handle(OpenScreenS2C data, IPayloadContext context)
    {
        context.enqueueWork(()->{
            PlayerAttackBehavior.openGui(data.difficulty,data.mathQuestionID);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
