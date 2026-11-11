package com.qpibara.Qpibara.event.dto;

public record TimerRequest(
        Long deltaSeconds,
        Long absoluteSeconds
) {
}
