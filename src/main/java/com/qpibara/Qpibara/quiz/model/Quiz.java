package com.qpibara.Qpibara.quiz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes", schema = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String comment;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("serialNumber ASC")
    @Builder.Default
    private List<Stage> stages = new ArrayList<>();

    public void setStages(List<Stage> stages) {
        this.stages.clear();
        if (stages != null) {
            this.stages.addAll(stages);
            this.stages.forEach(stage -> stage.setQuiz(this));
        }
    }
}
