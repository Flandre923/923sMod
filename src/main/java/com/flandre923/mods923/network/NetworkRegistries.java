package com.flandre923.mods923.network;

import com.flandre923.mods923.ExampleMod;
import com.flandre923.mods923.network.packet.c2s.MathQuestionAnswerMessage;
import com.flandre923.mods923.network.packet.s2c.OpenScreenS2C;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class NetworkRegistries {
    @SubscribeEvent
    public static void registerMessage(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar(ExampleMod.MODID);

        // to server
        registrar.playBidirectional(
                MathQuestionAnswerMessage.TYPE,
                MathQuestionAnswerMessage.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        MathQuestionAnswerMessage::handle
                )
        );

        // to client

        registrar.playBidirectional(
                OpenScreenS2C.TYPE,
                OpenScreenS2C.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        OpenScreenS2C::handle,
                        null
                )
        );

    }


}
