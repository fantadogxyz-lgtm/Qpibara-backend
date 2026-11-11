package com.qpibara.Qpibara.event.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "events", schema = "event")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String comment;
    private String place;
    private Instant eventDate;

    private Double balance;
    private Long authorId;
    private Long quizId;

    private Boolean isTeamsEnabled;
    private Integer plannedTeamCount;
    private Boolean isMembersEnabled;
    private Integer plannedMemberCount;
    private Boolean isScoringEnabled;
    private Boolean isAttendanceEnabled;

    @CreationTimestamp
    private Instant createdAt;
}