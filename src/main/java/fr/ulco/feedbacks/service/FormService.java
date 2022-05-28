package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.Form;

import java.util.List;

public interface FormService {

    Form addForm(FormDto formDto) throws Exception;
    List<Form> getAllMyFormsAsAnAuthenticatedUser();
    List<Form> getAllMyRequestsAsAnAuthenticatedUser();
    Answer addAnswer(Long id, Long questionId, AnswerDto answerDto) throws Exception;

    List<Form> getAllForms();
    void deleteAllForms();

    void deleteFormById(Long id) throws Exception;
    Form getById(Long id) throws Exception;

    void editAnswer(Long formId, Long questionId, Long answerId, AnswerDto answerDto) throws Exception;


    /*
    void addQuestion(Long id, QuestionDto question);

    void deleteQuestionById(Long id, Long questionId);

    void deleteAnswer(Long id, Long questionId, Long answerId);
    */
}