package com.qpibara.Qpibara.quiz.dto;

import java.util.List;

public record StageResponse(
        Long id,
        String name,
        String type,
        Integer serialNumber,
        String settings,
        List<QuestionResponse> questions
) {
}
