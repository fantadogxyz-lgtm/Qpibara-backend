package com.qpibara.Qpibara.event.dto;

public record SessionAnswerRequest(
        Long questionId,
        String answerText,
        Boolean isCorrect,
        Integer pointsAwarded,
        Long teamId,
        Long memberId
) {
}
