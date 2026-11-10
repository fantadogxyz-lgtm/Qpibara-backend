package com.qpibara.Qpibara.quiz.dto;

import java.time.Instant;
import java.util.List;

public record QuizResponse(
        Long id,
        String name,
        String description,
        String comment,
        List<StageResponse> stages,
        Instant createdAt,
        Instant updatedAt
) {
}
