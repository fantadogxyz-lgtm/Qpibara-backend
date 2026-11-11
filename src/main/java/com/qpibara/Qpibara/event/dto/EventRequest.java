package com.qpibara.Qpibara.event.dto;

import java.time.Instant;

public record EventRequest (
        String name,
        String description,
        String comment,
        String place,
        Instant eventDate,
        Double balance,
        Long quizId,
        Boolean isTeamsEnabled,
        Integer plannedTeamCount,
        Boolean isMembersEnabled,
        Integer plannedMemberCount,
        Boolean isScoringEnabled,
        Boolean isAttendanceEnabled
){

}
