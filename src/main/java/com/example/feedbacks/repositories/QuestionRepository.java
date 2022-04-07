package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Question;
import java.util.ArrayList;

public interface QuestionRepository {
    void addQuestion(Question question);
    ArrayList<Question> getAllQuestions();
    Question getQuestion(Integer id);
}
