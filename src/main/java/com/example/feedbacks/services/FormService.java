package com.example.feedbacks.services;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import com.example.feedbacks.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FormService {

    @Qualifier("memory")
    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public void addForm(Form form) {
        //TODO auto generate id
        form.setId(1);
        this.formRepository.addForm(form);
    }

    public ArrayList<Form> getAllForms() {
        return this.formRepository.getAllForms();
    }

    public Form getFormById(Integer formId) {
        return this.formRepository.getFormById(formId);
    }

    public HashMap<Integer, ArrayList<Answer>> getAllAnswers(Integer formId) {
        return this.formRepository.getFormById(formId).getAnswers();
    }

    public ArrayList<Answer> getAnswers(Integer formId, Integer questionId) {
        return this.formRepository.getAnswersOfQuestion(formId, questionId);
    }

    public void addAnswer(Integer formId, Integer questionId, Answer answer) {
        this.formRepository.addAnswer(formId, questionId, answer);
    }
}
