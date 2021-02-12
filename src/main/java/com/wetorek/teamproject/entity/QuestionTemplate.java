package com.wetorek.teamproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "question_templates")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionTemplate {
    @EqualsAndHashCode.Include
    private final UUID uuid = UUID.randomUUID();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private int maxPoints;
    private int correctAnswers;
    @ManyToOne
    @JoinColumn(name = "test_template_id")
    private TestTemplate testTemplate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionTemplate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OptionTemplate> options = new HashSet<>();

    public void attachParentEntities(TestTemplate testTemplate) {
        this.testTemplate = testTemplate;
        options.forEach(optionTemplate -> optionTemplate.setQuestionTemplate(this));
    }

    public void removeOptionTemplate(OptionTemplate optionTemplate) {
        options.remove(optionTemplate);
    }

}