package com.qpibara.Qpibara.event.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teams", schema = "event")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer points;
    private Boolean isPresent; // Отметка присутствия

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}