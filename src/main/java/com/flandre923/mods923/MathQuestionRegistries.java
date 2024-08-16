package com.flandre923.mods923;

import com.flandre923.mods923.mathQuestion.Answer;
import com.flandre923.mods923.mathQuestion.MathQuestion;
import com.flandre923.mods923.mathQuestion.StringMathQuestion;
import com.flandre923.mods923.mathQuestion.TextureMathQuestion;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathQuestionRegistries {
    public static Map<Integer, List<MathQuestion<?>>> mathQuestionMap = new HashMap<>();
    // textures
    public static TextureMathQuestionData tMq11 = new TextureMathQuestionData(ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"math_question/t1.png"),40,10,296,96);

    // file res
    public static MathQuestion<String> mq11 = initStringMathQuestion(0,1,"1+1=?",new Answer[]{new Answer(0,"2"),new Answer(1,"3"),new Answer(2,"4"),new Answer(3,"5")},0);
    public static MathQuestion<ResourceLocation> mq12 = initTextureMathQuestion(1,1,new Answer[]{new Answer(0,"2"),new Answer(1,"3"),new Answer(2,"4"),new Answer(3,"5")},0,tMq11);



    public static void init()
    {
        registerMathQuestion(1,List.of(mq11,mq12));
    }

    public static StringMathQuestion initStringMathQuestion(int id, int difficulty, String questions, Answer[] answers, int indexOfCorrect){
        return new StringMathQuestion(id,difficulty,questions,List.of(answers),answers[indexOfCorrect]);
    }

    public static TextureMathQuestion initTextureMathQuestion(int id, int difficulty, Answer[] answers, int indexOfCorrect,
                                                              TextureMathQuestionData data ){
        return new TextureMathQuestion(id,difficulty,List.of(answers),answers[indexOfCorrect],data.texture(),data.x(),data.y(),data.width(), data.height());
    }

    public static void registerMathQuestion(int difficulty, List<MathQuestion<?>> mathQuestions)
    {
        mathQuestionMap.put(difficulty, mathQuestions);
    }


    public static record TextureMathQuestionData(ResourceLocation texture, int x, int y, int width, int height){
    }
}
