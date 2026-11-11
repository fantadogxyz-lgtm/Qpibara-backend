package com.qpibara.Qpibara.event.dto;

import java.time.Instant;

public record EventShortInfoResponse(
        Long id,
        String name,
        Instant eventDate,
        String place
) {
}
