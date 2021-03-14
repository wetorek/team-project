package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "question_templates")
@AllArgsConstructor
@Getter
@Setter
public class QuestionTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private int maxPoints;
    private int correctAnswers;
    private int allAnswers;
    @ManyToOne
    @JoinColumn(name = "test_template_id")
    private TestTemplate testTemplate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionTemplate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OptionTemplate> options = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionTemplate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    @PersistenceConstructor
    public QuestionTemplate() {
    }

    public void attachParentEntities(TestTemplate testTemplate) {
        this.testTemplate = testTemplate;
        options.forEach(optionTemplate -> optionTemplate.setQuestionTemplate(this));
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeOptionTemplate(OptionTemplate optionTemplate) {
        options.remove(optionTemplate);
    }

}