package com.qpibara.Qpibara.quiz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stages", schema = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Integer serialNumber;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String settings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public void setQuestions(List<Question> questions) {
        if (this.questions == null) this.questions = new ArrayList<>();
        this.questions.clear();
        if (questions != null) {
            this.questions.addAll(questions);
            this.questions.forEach(q -> q.setStage(this));
        }
    }
}
