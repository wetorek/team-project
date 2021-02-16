package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "optionTemplate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Option> options;

    public void addOption(Option option) {
        options.add(option);
    }
}
