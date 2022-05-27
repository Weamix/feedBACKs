package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Form;

import java.util.List;

public interface FormService {

    Form addForm(FormDto formDto);
    List<Form> getAllMyFormsAsAnAuthenticatedUser();
    List<Form> getAllMyRequestsAsAnAuthenticatedUser();
    void addAnswer(Long id, Long questionId, AnswerDto answerDto);

    List<Form> getAllForms();
    void deleteAllForms();

    /*

    FormDto getById(Long id);

    void addQuestion(Long id, QuestionDto question);

    void deleteFormById(Long id);

    void deleteQuestionById(Long id, Long questionId);

    void deleteAnswer(Long id, Long questionId, Long answerId);
    */
}