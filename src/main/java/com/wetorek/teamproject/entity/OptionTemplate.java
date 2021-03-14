package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "option_templates")
@AllArgsConstructor
@Data
public class OptionTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answerText;
    private boolean correct;
    @ManyToOne
    @JoinColumn(name = "question_template_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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
