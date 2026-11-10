package com.qpibara.Qpibara.quiz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matching_pairs", schema = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchingPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "left_element")
    private String leftElement;

    @Column(name = "right_element")
    private String rightElement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}