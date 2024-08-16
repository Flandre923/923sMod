package com.flandre923.mods923.mathQuestion;

import java.util.List;

public  interface IMathQuestion <T> {
    T getQuestion();
    int getId();
    List<Answer> getOptions();
    Answer getCorrectAnswer();
    int getDifficulty();
}
