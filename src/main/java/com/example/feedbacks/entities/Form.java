package com.example.feedbacks.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String author;

    @ElementCollection
    @OneToMany
    private Set<Question> questions;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "answer_to_question_mapping", joinColumns = {
            @JoinColumn(name = "question_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "answer_id", referencedColumnName = "id")
    })
    @MapKey(name = "question_id")*/

    @ElementCollection
    @OneToMany(mappedBy = "question_id")
    @MapKey(name = "question_id")
    private Map<Integer, ArrayList<Answer>> answers;
}
