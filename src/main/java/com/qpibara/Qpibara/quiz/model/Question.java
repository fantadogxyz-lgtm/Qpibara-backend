package com.qpibara.Qpibara.quiz.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions", schema = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Integer points;
    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @Enumerated(EnumType.ORDINAL)
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AnswerOption> answerOptions = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RightAnswer> rightAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MatchingPair> matchingPairs = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Attachment> attachments = new ArrayList<>();

    public void setAnswerOptions(List<AnswerOption> options) {
        this.answerOptions.clear();
        if (options != null) {
            this.answerOptions.addAll(options);
            this.answerOptions.forEach(o -> o.setQuestion(this));
        }
    }

    public void setRightAnswers(List<RightAnswer> answers) {
        this.rightAnswers.clear();
        if (answers != null) {
            this.rightAnswers.addAll(answers);
            this.rightAnswers.forEach(a -> a.setQuestion(this));
        }
    }

    public void setMatchingPairs(List<MatchingPair> pairs) {
        this.matchingPairs.clear();
        if (pairs != null) {
            this.matchingPairs.addAll(pairs);
            this.matchingPairs.forEach(p -> p.setQuestion(this));
        }
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments.clear();
        if (attachments != null) {
            this.attachments.addAll(attachments);
            this.attachments.forEach(a -> a.setQuestion(this));
        }
    }


}