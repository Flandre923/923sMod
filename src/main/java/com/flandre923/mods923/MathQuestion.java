package com.flandre923.mods923;

import net.minecraft.network.FriendlyByteBuf;

import java.util.List;

public class MathQuestion {
    private final int id;
    private final int difficulty;
    private final String question;
    private final List<Answer> options;
    private final Answer correctAnswer;

    public MathQuestion(int id, int difficulty, String question, List<Answer> options, Answer correctAnswer) {
        this.id = id;
        this.difficulty = difficulty;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;

    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getOptions() {
        return options;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public int getId(){
        return this.id;
    }

}
