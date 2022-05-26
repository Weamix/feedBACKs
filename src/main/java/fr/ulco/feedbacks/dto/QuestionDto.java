package fr.ulco.feedbacks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    private Long questionId;
    private String content;
    private Instant createdAt;
    private List<AnswerDto> answers;
}
