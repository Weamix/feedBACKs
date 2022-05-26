package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.User;

import java.util.List;

public interface FormService {

    List<FormDto> getAll();
    FormDto getById(Long id);

    void addForm(FormDto form, String username);

    void addQuestion(Long id, QuestionDto question);

    void deleteFormById(Long id);

    void deleteQuestionById(Long id, Long questionId);

    void addAnswer(Long id, Long questionId, AnswerDto answer);

    void deleteAnswer(Long id, Long questionId, Long answerId);

    List<Form> getAllFormsOfAuthenticatedUser(String username);
}