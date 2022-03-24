package com.example.feedbacks.services;

import com.example.feedbacks.entities.Question;
import com.example.feedbacks.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionService{

    @Qualifier("memory")
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(final QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question) {
        questionRepository.addQuestion(question);
    }

    public ArrayList<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }
}
