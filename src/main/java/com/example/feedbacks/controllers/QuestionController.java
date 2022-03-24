package com.example.feedbacks.controllers;

import com.example.feedbacks.entities.Question;
import com.example.feedbacks.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(final QuestionService questionService){
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    void addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ArrayList<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    //DELETE

    //PUT

}