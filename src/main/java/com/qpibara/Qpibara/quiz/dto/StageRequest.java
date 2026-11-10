package com.qpibara.Qpibara.quiz.dto;

import java.util.List;

public record StageRequest(
        Long id,
        String name,
        String type,
        Integer serialNumber,
        String settings,
        List<QuestionRequest> questions
) {}
