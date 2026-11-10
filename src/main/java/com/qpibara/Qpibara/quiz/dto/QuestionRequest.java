package com.qpibara.Qpibara.quiz.dto;

import com.qpibara.Qpibara.quiz.model.QuestionType;

import java.util.List;

public record QuestionRequest(
        String text,
        QuestionType type,
        Integer points,
        String explanation,
        List<AnswerOptionRequest> answerOptions,
        List<RightAnswerRequest> rightAnswers,
        List<MatchingPairRequest> matchingPairs
) {

}
