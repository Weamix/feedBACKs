package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;

import java.util.ArrayList;
import java.util.List;

public interface FormRepository {
    void addForm(Form form);
    Form getFormById(Integer formId);
    ArrayList<Form> getAllForms();

    List<Answer> getAnswers(Integer formId, Integer questionId);

    void addAnswer(Integer formId, Integer questionId, Answer answer);
}
