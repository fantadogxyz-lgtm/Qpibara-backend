package com.qpibara.Qpibara.event.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "session_answers", schema = "event")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;
    private String answerText;
    private Boolean isCorrect;
    private Integer pointsAwarded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}