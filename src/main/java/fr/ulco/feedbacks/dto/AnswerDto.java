package fr.ulco.feedbacks.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class AnswerDto {
    private Long answerId;
    @NonNull private String content;
    private Long userId;
}
