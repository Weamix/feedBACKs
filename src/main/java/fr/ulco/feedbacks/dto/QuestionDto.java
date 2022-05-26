package fr.ulco.feedbacks.dto;

import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class QuestionDto {
    private Long questionId;
    @NonNull private String content;
    @Builder.Default private Instant createdAt = Instant.now();
    @Builder.Default private List<AnswerDto> answers = new ArrayList<>();
}
