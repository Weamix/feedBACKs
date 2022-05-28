package fr.ulco.feedbacks.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;

    private String username;

    @NotBlank(message = "Please enter a the question content")
    private String content;

    private Instant createdAt;
    private Instant updatedAt;

    private Long userId;
}
