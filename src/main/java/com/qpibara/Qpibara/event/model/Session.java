package com.qpibara.Qpibara.event.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions", schema = "event")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private String notes; // Блокнот ведущего

    private Long currentStageId; // На каком этапе остановились
    private Long currentQuestionId; // На каком вопросе

    @CreationTimestamp
    private Instant startedAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SessionAnswer> answers = new ArrayList<>();

    private Long totalTimeSeconds;

    // Хелперы для синхронизации (как в квизе)
    public void setTeams(List<Team> teams) {
        this.teams.clear();
        if (teams != null) {
            this.teams.addAll(teams);
            this.teams.forEach(t -> t.setSession(this));
        }
    }
}