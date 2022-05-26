package fr.ulco.feedbacks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {
    private Long answerId;
    private String content;
    private Long questionId;
    private Long userId;
}
