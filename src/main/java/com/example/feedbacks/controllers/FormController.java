package com.example.feedbacks.controllers;

import com.example.feedbacks.entities.Answer;
import com.example.feedbacks.entities.Form;
import com.example.feedbacks.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/form")
public class FormController {
    private FormService formService;

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
        return formService.getAllForms();
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Form getFormById(@PathVariable final Integer id){
        return this.formService.getFormById(id);
    }

    @GetMapping(value="/{formId}/answers/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Answer> getAnswersByQuestionId(@PathVariable final Integer formId, @PathVariable final Integer questionId){
        return this.formService.getAnswers(formId, questionId);
    }

    @PostMapping(value="/{formId}/question/{questionId}/answer/")
    @ResponseStatus(HttpStatus.CREATED)
    // @RequestBody Map<Integer FormId, Answer answer>
    public void addAnswer(@PathVariable final Integer formId, @PathVariable final Integer questionId, @RequestBody Answer answer){
        this.formService.addAnswer(formId, questionId, answer);
    }
}
