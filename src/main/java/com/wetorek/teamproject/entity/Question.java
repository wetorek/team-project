package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
