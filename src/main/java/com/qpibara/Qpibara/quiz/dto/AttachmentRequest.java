package com.qpibara.Qpibara.quiz.dto;

import java.util.UUID;

public record AttachmentRequest(
        UUID id,
        String uri,
        Long typeId,
        String typeTitle
) {
}
