package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import com.example.feedbacks.entities.Question;

import java.util.ArrayList;

public interface FormRepository {
    void addForm(Form form);

    Form getFormById(Integer formId);

    ArrayList<Form> getAllForms();

    ArrayList<Answer> getAnswersOfQuestion(Integer formId, Integer questionId);

    void addAnswer(Integer formId, Integer questionId, Answer answer);

    void addQuestion(Integer formId, Question question);

    void deleteQuestion(Integer formId, Integer questionId);

    void deleteAnswer(Integer formId, Integer questionId, Integer answerId);
}
