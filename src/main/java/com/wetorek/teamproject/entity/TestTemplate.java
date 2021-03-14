package com.wetorek.teamproject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "test_templates")
@AllArgsConstructor
@Data
public class TestTemplate {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testTemplate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<QuestionTemplate> questionTemplates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testTemplate")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Test> tests;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @PersistenceConstructor
    public TestTemplate() {
    }

    public void clearAndAddAll(Set<QuestionTemplate> questionTemplateSet) {
        questionTemplates.clear();
        questionTemplates.addAll(questionTemplateSet);
    }

    public void removeQuestionTemplate(QuestionTemplate questionTemplate) {
        questionTemplates.remove(questionTemplate);
    }

}
