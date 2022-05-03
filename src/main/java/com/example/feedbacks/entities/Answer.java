package com.example.feedbacks.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private Integer id;
    private String message;
    // stocké l'auteur car une question peut avoir une réponse par plusieurs personnes
    private String author;
}
