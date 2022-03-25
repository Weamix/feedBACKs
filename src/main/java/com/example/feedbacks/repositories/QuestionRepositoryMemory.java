package com.example.feedbacks.repositories;

import com.example.feedbacks.entities.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Qualifier("memory")
public class QuestionRepositoryMemory implements QuestionRepository {
    private ArrayList<Question> questions;

    public QuestionRepositoryMemory() {
        this.questions = new ArrayList<>();
    }

    @Override
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public ArrayList<Question> getAllQuestions(){
        return questions;
    }
}

