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

    // TODO: addQuestion (ne pas oublier de mettre à jour la map answers)
    void addQuestion(Integer formId, Question question);

    // TODO: Update ? (form, answer, question?)
}
