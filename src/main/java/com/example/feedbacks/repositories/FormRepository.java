package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Form;

import java.util.ArrayList;

public interface FormRepository {
    void addForm(Form form);
    Form getFormById(Integer formId);
    ArrayList<Form> getAllForms();
}
