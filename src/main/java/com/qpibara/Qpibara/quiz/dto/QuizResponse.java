package com.qpibara.Qpibara.quiz.dto;

import java.util.List;

public record QuizResponse(
        Long id,
        String name,
        String description,
        String comment,
        List<StageResponse> stages
) {
}
