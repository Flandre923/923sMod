package com.flandre923.mods923.network.packet.c2s;

import com.flandre923.mods923.*;
import com.flandre923.mods923.attack_counter.PlayerAttackBehavior;
import com.flandre923.mods923.mathQuestion.Answer;
import com.flandre923.mods923.mathQuestion.MathQuestion;
import com.flandre923.mods923.setup.MathQuestionRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;
import java.util.UUID;

public class MathQuestionAnswerMessage implements CustomPacketPayload {
    public final int id;
    public final int diffculty;
    public final UUID uuid;
    public final int MathQuestionID;
    public final int answerID;

    public static Type<MathQuestionAnswerMessage> TYPE =
            new Type<MathQuestionAnswerMessage>(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"math_question_c2s"));

    // stream codec
    public static final StreamCodec<FriendlyByteBuf, MathQuestionAnswerMessage> STREAM_CODEC =
            CustomPacketPayload.codec(MathQuestionAnswerMessage::write, MathQuestionAnswerMessage::new);



    public MathQuestionAnswerMessage(int playerId,UUID playerUuid,int difficulty,int mathQuestion,int answerID){
        this.id = playerId;
        this.uuid = playerUuid;
        this.diffculty = difficulty;
        this.MathQuestionID = mathQuestion;
        this.answerID = answerID;
    }

    public MathQuestionAnswerMessage(FriendlyByteBuf buf){
        this.id = buf.readInt();
        this.uuid = buf.readUUID();
        this.MathQuestionID = buf.readInt();
        this.answerID = buf.readInt();
        this.diffculty = buf.readInt();
    }

    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(this.id);
        pBuffer.writeUUID(this.uuid);
        pBuffer.writeInt(this.MathQuestionID);
        pBuffer.writeInt(this.answerID);
        pBuffer.writeInt(this.diffculty);
    }


    public static void handle(MathQuestionAnswerMessage data, IPayloadContext context){

        context.enqueueWork(()->{

            // server
            List<MathQuestion<?>> mathQuestions = MathQuestionRegistries.mathQuestionMap.get(data.diffculty);
            MathQuestion<?> mathQuestion = mathQuestions.get(data.MathQuestionID);
            Answer correctAnswer = mathQuestion.getCorrectAnswer();
            boolean isCorrect = data.answerID == correctAnswer.id;
            if (isCorrect){
                PlayerAttackBehavior.setAttackMutiDamageFlag(data.uuid,data.id);
            }else {
                PlayerAttackBehavior.setHurtFlag(data.uuid, data.id);
            }

        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
