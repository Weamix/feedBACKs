package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import com.example.feedbacks.entities.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface FormRepository {
    void addForm(Form form);

    Form getFormById(Integer formId);
    ArrayList<Form> getAllForms();

    HashMap<Integer, ArrayList<Answer>> getAllAnswers(Integer formId);
    ArrayList<Answer> getAnswers(Integer formId, Integer questionId);
    void addAnswer(Integer formId, Integer questionId, Answer answer);

    // TODO: addQuestion (ne pas oublier de mettre à jour la map answers)

    // TODO: Update ? (form, answer, question?)
}
