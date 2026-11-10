package com.qpibara.Qpibara.quiz.dto;

import com.qpibara.Qpibara.quiz.model.QuestionType;

import java.util.List;

public record QuestionRequest(
        Long id,
        String text,
        QuestionType type,
        Integer points,
        String explanation,
        List<AnswerOptionRequest> answerOptions,
        List<MatchingPairRequest> matchingPairs,
        List<AttachmentRequest> attachments
) {

}
