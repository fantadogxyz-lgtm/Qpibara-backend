package com.qpibara.Qpibara.quiz.dto;

public record AnswerOptionRequest(
        Long id, String text, Boolean isCorrect) {
}
