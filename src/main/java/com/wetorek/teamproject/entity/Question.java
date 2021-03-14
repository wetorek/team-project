package com.wetorek.teamproject.entity;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "questions")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Question {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private final Set<Option> options = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private int maxPoints;
    private int points;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "question_template_id")
    private QuestionTemplate questionTemplate;

    @PersistenceConstructor
    public Question (){

    }

    public void addOptions(Set<Option> options) {
        this.options.addAll(options);
    }
}
