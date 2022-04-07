package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        throw new IllegalArgumentException("Form with id " + formId + " not found");
    }

    @Override
    public ArrayList<Form> getAllForms() {
        return forms;
    }

    @Override
    public List<Answer> getAnswers(Integer formId, Integer questionId) {
        // todo : try  catch
        Map<Integer, ArrayList<Answer>> resAnswers = getFormById(formId).getAnswers();

        if (resAnswers == null){
            throw new IllegalArgumentException("There is no answer for this form question");
        }

        return resAnswers.get(questionId);
    }

    @Override
    public void addAnswer(Integer formId, Integer questionId, Answer answer) {
        //TODO fix this shit
        /*Form form = getFormById(formId);
        Map<Integer, ArrayList<Answer>> resAnswers = form.getAnswers();

        //ArrayList<Answer> answersOfQuestionId = form.getAnswers().get(questionId);

        if (resAnswers == null){
            resAnswers = new ArrayList<>();
            form.getAnswers().put(questionId, resAnswers);
        }

        resAnswers.put(Map<questionId, answer>);*/
    }
}
