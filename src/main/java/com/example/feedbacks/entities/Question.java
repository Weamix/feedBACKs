package com.example.feedbacks.entities;
import java.util.List;

public class Question {
    private Integer id;
    private String author;
    private String message;
    private List<Answer> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswer(List<Answer> answers) {
        this.answers = answers;
    }
}
