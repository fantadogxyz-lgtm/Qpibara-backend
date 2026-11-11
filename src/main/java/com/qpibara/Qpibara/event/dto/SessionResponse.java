package com.qpibara.Qpibara.event.dto;

import java.time.Instant;
import java.util.List;

public record SessionResponse(Long id,
                              Long eventId,
                              String notes,
                              Long currentStageId,
                              Long currentQuestionId,
                              Long totalTimeSeconds,
                              Instant startedAt,
                              List<TeamResponse> teams,
                              List<MemberResponse> members) {
}
