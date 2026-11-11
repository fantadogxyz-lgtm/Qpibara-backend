package com.qpibara.Qpibara.event.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members", schema = "event")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer points;
    private Boolean isPresent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}