package com.qpibara.Qpibara.quiz.dto;

import com.qpibara.Qpibara.quiz.model.QuestionType;

import java.util.List;

public record QuestionResponse(
        Long id,
        String text,
        QuestionType type,
        Integer points,
        String explanation,
        List<AnswerOptionResponse> answerOptions,
        List<MatchingPairResponse> matchingPairs,
        List<AttachmentResponse> attachments
) {
}
