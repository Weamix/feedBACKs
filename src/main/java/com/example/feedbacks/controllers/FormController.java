package com.example.feedbacks.controllers;

import com.example.feedbacks.entities.Form;
import com.example.feedbacks.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping(value="/form/{formId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Form getFormById(@PathVariable final Integer formId){
        return this.formService.getFormById(formId);
    }

}
