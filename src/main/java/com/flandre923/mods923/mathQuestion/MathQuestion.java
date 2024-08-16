package com.flandre923.mods923.mathQuestion;

import java.util.List;

public abstract class MathQuestion <T> implements IMathQuestion<T>{
    private final int id;
    private final int difficulty;
    private final List<Answer> options;
    private final Answer correctAnswer;

    public MathQuestion(int id, int difficulty, List<Answer> options, Answer correctAnswer) {
        this.id = id;
        this.difficulty = difficulty;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public int getDifficulty() {
        return difficulty;
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
