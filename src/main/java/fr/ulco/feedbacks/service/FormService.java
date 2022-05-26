package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;

import java.util.List;

public interface FormService {

    List<FormDto> getAll();
    FormDto getById(Long id);

    void addForm(FormDto form);

    void addQuestion(Long id, QuestionDto question);

    void deleteFormById(Long id);

    void deleteQuestionById(Long id, Long questionId);

    void addAnswer(Long id, Long questionId, AnswerDto answer);
}