package com.example.feedbacks.controllers;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import com.example.feedbacks.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/form")
public class FormController {
    private final FormService formService;

    @Autowired
    public FormController(final FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addForm(@RequestBody Form form) {
        this.formService.addForm(form);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ArrayList<Form> getAllForms(){
        return this.formService.getAllForms();
    }

    @GetMapping(value="/{formId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Form getFormById(@PathVariable final Integer formId){
        try {
            return this.formService.getFormById(formId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Form "+ formId +" not found", e);
        }
    }

    @GetMapping(value="/{formId}/answer/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<Integer, ArrayList<Answer>> getAnswersByFormId(@PathVariable final Integer formId){
        try {
            return this.formService.getAllAnswers(formId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{formId}/question/{questionId}/answer/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ArrayList<Answer> getAnswers(@PathVariable final Integer formId, @PathVariable final Integer questionId){
        try {
            return this.formService.getAnswers(formId, questionId);
        } catch (IllegalArgumentException e) {
            // ICI on prends en compte si le form n'existe pas
            // et aussi quand il n'y a pas de réponses pour la question
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/{formId}/question/{questionId}/answer/")
    @ResponseStatus(HttpStatus.CREATED)
    // @RequestBody Map<Integer FormId, Answer answer>
    public void addAnswer(@PathVariable final Integer formId, @PathVariable final Integer questionId, @RequestBody Answer answer){
        try {
            this.formService.addAnswer(formId, questionId, answer);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
