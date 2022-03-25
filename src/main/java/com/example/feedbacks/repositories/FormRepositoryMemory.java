package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Form;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FormRepositoryMemory implements FormRepository{
    public ArrayList<Form> forms;

    public FormRepositoryMemory() {
        this.forms = new ArrayList<>();
    }

    @Override
    public void addForm(Form form) {
        this.forms.add(form);
    }

    @Override
    public Form getFormById(Integer formId) {
        for(Form f : forms){
            if(f.getId().equals(formId)){
                return f;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Form> getAllForms() {
        return forms;
    }
}
