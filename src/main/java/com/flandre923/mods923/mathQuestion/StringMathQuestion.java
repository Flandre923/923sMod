package com.flandre923.mods923.mathQuestion;

import java.util.List;

public class StringMathQuestion extends MathQuestion<String> {
    private final String question;

    public StringMathQuestion(int id, int difficulty, String question, List<Answer> options, Answer correctAnswer) {
        super(id, difficulty, options, correctAnswer);
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

}
