package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "option_templates")
@AllArgsConstructor
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

    @PersistenceConstructor
    public OptionTemplate() {
    }

    public void addOption(Option option) {
        options.add(option);
    }
}
