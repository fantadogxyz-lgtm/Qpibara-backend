package com.qpibara.Qpibara.quiz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachment_type", schema = "quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String title;
}
