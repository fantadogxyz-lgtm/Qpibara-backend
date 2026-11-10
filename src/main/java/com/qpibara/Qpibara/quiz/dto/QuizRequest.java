package com.qpibara.Qpibara.quiz.dto;

import java.util.List;

public record QuizRequest(
        String name,
        String description,
        String comment,
        List<StageRequest> stages
) {}