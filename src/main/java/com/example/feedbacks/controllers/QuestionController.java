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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(@RequestBody Question question){
        this.questionService.addQuestion(question);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ArrayList<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    //DELETE

    //PUT

}