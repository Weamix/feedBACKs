package fr.ulco.feedbacks.Repository;

import fr.ulco.feedbacks.Entity.Answer;
import fr.ulco.feedbacks.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);
    List<Answer> findAllByQuestionId(Long questionId);
}
