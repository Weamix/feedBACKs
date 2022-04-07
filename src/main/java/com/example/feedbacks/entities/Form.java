package com.example.feedbacks.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Form {
    private Integer id;
    private String author;

    private Set<Question> questions;
    private Map<Integer, ArrayList<Answer>> answers;
}
