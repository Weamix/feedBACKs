package com.example.feedbacks.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Form {
    private Integer id;
    private String author;
    private List<Integer> questions;
}

