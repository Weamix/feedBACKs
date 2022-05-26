package fr.ulco.feedbacks.dto;

import fr.ulco.feedbacks.entity.Question;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class FormDto {
    private Long formId;
    @NonNull private String formName;
    @Builder.Default private List<Question> questions = new ArrayList<>();
    @Builder.Default private Instant createdAt = Instant.now();
    @Builder.Default private Instant updatedAt = Instant.now();
    private Long userId;
    @NonNull  private List<String> recipients;
}
