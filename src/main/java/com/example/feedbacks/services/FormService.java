package com.example.feedbacks.services;

import com.example.feedbacks.entities.Form;
import com.example.feedbacks.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FormService {

    private FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public void addForm(Form form) {
        //TODO auto generate id
        form.setId(1);
        this.formRepository.addForm(form);
    }

    public Form getFormById(Integer formId) {
        return this.formRepository.getFormById(formId);
    }

    public ArrayList<Form> getAllForms() {
        return this.formRepository.getAllForms();

    }
}
