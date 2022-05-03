package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import com.example.feedbacks.entities.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Repository
@Qualifier("memory")
public class FormRepositoryMemory implements FormRepository {
    public ArrayList<Form> forms;

    public FormRepositoryMemory() {
        this.forms = new ArrayList<>();
    }

    @Override
    public void addForm(Form form) {
        // si le formulaire ajouté n'a pas de réponses, on l'ajoute avec une liste vide
        if (form.getAnswers() == null) {
            form.setAnswers(new HashMap<>());

            // add empty answers to each questions
            for (Question q : form.getQuestions()) {
                form.getAnswers().put(q.getId(), new ArrayList<>());
            }
        }
        this.forms.add(form);
    }

    @Override
    public ArrayList<Form> getAllForms() {
        return forms;
    }

    @Override
    public Form getFormById(Integer formId) {
        for (Form f : forms) {
            if (f.getId().equals(formId)) {
                return f;
            }
        }

        // est-ce qu'on peut faire autrement pour la gestion des exceptions ?
        // Il faudrait peut etre créer une classe d'exception pour les erreurs (genre FormNotFoundException)
        throw new IllegalArgumentException("Form with id " + formId + " not found");
    }

    @Override
    public ArrayList<Answer> getAnswersOfQuestion(Integer formId, Integer questionId) {
        HashMap<Integer, ArrayList<Answer>> formAnswers = this.getFormById(formId).getAnswers();

        ArrayList<Answer> questionAnswers = formAnswers.get(questionId);

        // pas ouf, il faut voir si on peut faire mieux avec un nouveau endpoint GET /form/1/question
        if (questionAnswers == null) {
            throw new IllegalArgumentException("There is no answer for question id " + questionId + " in form id " + formId);
        }

        return questionAnswers;
    }

    @Override
    public void addAnswer(Integer formId, Integer questionId, Answer answer) {
        // on dépend de getAllAnswers qui dépends elle-même de getFormById.....

        Set<Question> formQuestions = this.getFormById(formId).getQuestions();
        HashMap<Integer, ArrayList<Answer>> formAnswers = this.getFormById(formId).getAnswers();

        // on vérifie que questionId existe bien dans le formulaire
        for (Question q : formQuestions) {
            if (q.getId().equals(questionId)) {
                formAnswers.get(questionId).add(answer);
                return;
            }
        }

        throw new IllegalArgumentException("There is no question with id " + questionId + " in form id " + formId);
    }

    @Override
    public void addQuestion(Integer formId, Question question) {

    }
}
