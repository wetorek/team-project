package com.wetorek.teamproject.entity;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "questions")
@AllArgsConstructor
@Data
@Builder
public class Question {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private Set<Option> options;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private int maxPoints;
    private int points;
    @ManyToOne
    @JoinColumn(name = "test_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Test test;

    @ManyToOne
    @JoinColumn(name = "question_template_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private QuestionTemplate questionTemplate;

    @PersistenceConstructor
    public Question() {
    }
}
