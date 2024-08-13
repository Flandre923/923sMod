package com.flandre923.mods923;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathQuestionRegistries {
    public static Map<Integer, List<MathQuestion>> mathQuestionMap = new HashMap<>();

    public static MathQuestion mq1 = initMathQuestion(0,1,"1+1=?",new Answer[]{new Answer(0,"2"),new Answer(1,"3"),new Answer(2,"4"),new Answer(3,"5")},0);


    public static void init()
    {
        registerMathQuestion(1,List.of(mq1));
    }

    public static MathQuestion initMathQuestion(int id,int difficulty,String questions,Answer[] answers,int indexOfCorrect){
        return new MathQuestion(id,difficulty,questions,List.of(answers),answers[indexOfCorrect]);
    }

    public static void registerMathQuestion(int difficulty, List<MathQuestion> mathQuestions)
    {
        mathQuestionMap.put(difficulty, mathQuestions);
    }
}
