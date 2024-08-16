package com.flandre923.mods923.mathQuestion;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class TextureMathQuestion extends MathQuestion<ResourceLocation>{

    private final int width;
    private final int height;
    private final int startX;
    private final int startY;
    public final ResourceLocation mathTexture;

    public TextureMathQuestion(int id, int difficulty, List<Answer> options, Answer correctAnswer, ResourceLocation mathTexture,
                               int x, int y, int width, int height) {
        super(id, difficulty, options, correctAnswer);
        this.mathTexture = mathTexture;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
    }

    @Override
    public ResourceLocation getQuestion() {
        return mathTexture;
    }

    // x,y.w,h get
    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}
