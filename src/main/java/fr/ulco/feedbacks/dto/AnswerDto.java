package fr.ulco.feedbacks.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class AnswerDto {
    private int answerId;
    @NonNull private String content;
    private Long userId;
}
