package com.qpibara.Qpibara.quiz.model;

public enum QuestionType{
    SINGLE_CHOICE(0),
    MULTIPLE_CHOICE(1),
    TEXT_INPUT(2),
    MATCHING(3);

    private int value;
    QuestionType(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }

    public static QuestionType fromValue(int value){
        for (QuestionType questionType : QuestionType.values()){
            if (questionType.value == value){
                return questionType;
            }
        }
        return null;
    }

}
