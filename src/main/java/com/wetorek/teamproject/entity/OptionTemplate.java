package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "option_templates")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answerText;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_template_id")
    private QuestionTemplate questionTemplate;


}
