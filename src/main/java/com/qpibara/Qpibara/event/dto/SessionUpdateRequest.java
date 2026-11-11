package com.qpibara.Qpibara.event.dto;

public record SessionUpdateRequest(
        String notes,
        Long currentStageId,
        Long currentQuestionId
) {
}
