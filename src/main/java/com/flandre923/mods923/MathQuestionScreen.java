package com.flandre923.mods923;

import com.flandre923.mods923.mathQuestion.MathQuestion;
import com.flandre923.mods923.mathQuestion.StringMathQuestion;
import com.flandre923.mods923.mathQuestion.TextureMathQuestion;
import com.flandre923.mods923.network.packet.c2s.MathQuestionAnswerMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MathQuestionScreen extends Screen {

    public final MathQuestion<?> mathQuestion;
    public int seconds;
    public final LocalPlayer player;

    protected int imageWidth = 176;
    protected int imageHeight = 166;

    public ScheduledExecutorService executorService;

    public MathQuestionScreen(Component title, MathQuestion<?> mathQuestion, int seconds) {
        super(title);
        this.mathQuestion = mathQuestion;

        this.imageWidth = this.width /2;
        this.imageHeight = this.height/2;
        this.seconds = seconds;

        player = Minecraft.getInstance().player;


        this.executorService = Executors.newSingleThreadScheduledExecutor();

        this.executorService.scheduleAtFixedRate(()->{
            MathQuestionScreen.this.seconds--;
            if (MathQuestionScreen.this.seconds<=0){
                MathQuestionScreen.this.player.closeContainer();
                PacketDistributor.sendToServer(new MathQuestionAnswerMessage(player.getId(),player.getUUID(),mathQuestion.getDifficulty(),mathQuestion.getId(),-1));
                MathQuestionScreen.this.executorService.shutdown();
            }
        },0,1, TimeUnit.SECONDS);

    }

    private <T extends AbstractWidget > void addButton(T beaconButton) {
        this.addRenderableWidget(beaconButton);
    }


    @Override
    protected void init() {
        super.init();
        // 添加文字
        addButton(new Button(this.width/2-50-70, this.height/2-10, 50, 20,0,mathQuestion));
        addButton(new Button(this.width/2-50,this.height/2-10,50,20,1,mathQuestion));
        addButton(new Button(this.width/2-50+70,this.height/2-10,50,20,2,mathQuestion));
        addButton(new Button(this.width/2-50+140,this.height/2-10,50,20,3,mathQuestion));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if( mathQuestion instanceof  StringMathQuestion stringMathQuestion){
            guiGraphics.drawCenteredString(this.font,String.valueOf(this.seconds),this.width/2-10,40,0xFFFFFF);
            guiGraphics.drawCenteredString(this.font,stringMathQuestion.getQuestion(),this.width/2-10,60,0xFFFFFF);

        }else if (mathQuestion instanceof TextureMathQuestion textureMathQuestion){
            guiGraphics.blit(textureMathQuestion.getQuestion(), textureMathQuestion.getStartX(),textureMathQuestion.getStartY(),
                    0.0F, 0.0F, textureMathQuestion.getWidth(), textureMathQuestion.getHeight(), textureMathQuestion.getWidth(), textureMathQuestion.getHeight());

        }

    }

    class Button extends AbstractButton{

        MathQuestion<?> mathQuestion;
        int indexOfAnswer;
        public Button(int x, int y, int width, int height, int indexOfAnswer, MathQuestion<?> mathQuestion) {
            super(x, y, width, height, Component.literal(mathQuestion.getOptions().get(indexOfAnswer).getContent()));
            this.mathQuestion = mathQuestion;
            this.indexOfAnswer = indexOfAnswer;
        }

        @Override
        public void onPress() {
            // TODO
            // send pack to server to confirm right ?
            // if not right player hurt 1 hp
            // else player get muti damage
            //  if fail reset attack counter
            //  and reset laskTick
            player.closeContainer();
            PacketDistributor.sendToServer(new MathQuestionAnswerMessage(player.getId(),player.getUUID(),mathQuestion.getDifficulty(),mathQuestion.getId(),mathQuestion.getOptions().get(indexOfAnswer).getId()));
            // close timer
            if(!MathQuestionScreen.this.executorService.isShutdown()){
                MathQuestionScreen.this.executorService.shutdown();
            }
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

        }
    }
}
