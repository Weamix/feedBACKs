package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Form;

import java.util.List;

public interface FormService {

    Form addForm(FormDto formDto);
    List<Form> getAllMyFormsAsAnAuthenticatedUser();
    List<Form> getAllMyRequestsAsAnAuthenticatedUser();
    void addAnswer(Long id, Long questionId, AnswerDto answerDto) throws Exception;

    List<Form> getAllForms();
    void deleteAllForms();

    void deleteFormById(Long id);
    Form getById(Long id);



    /*
    FormDto getById(Long id);

    void addQuestion(Long id, QuestionDto question);

    void deleteQuestionById(Long id, Long questionId);

    void deleteAnswer(Long id, Long questionId, Long answerId);
    */
}