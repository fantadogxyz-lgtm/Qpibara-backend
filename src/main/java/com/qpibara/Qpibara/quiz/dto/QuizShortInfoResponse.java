package com.qpibara.Qpibara.quiz.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record QuizShortInfoResponse(
        Long id,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
}
