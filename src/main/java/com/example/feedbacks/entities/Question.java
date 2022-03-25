package com.example.feedbacks.entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private Integer id;
    private String author;
    private String message;
    //private List<Answer> answers;
}
