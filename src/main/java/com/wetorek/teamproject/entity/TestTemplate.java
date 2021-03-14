package com.wetorek.teamproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "test_templates")
@AllArgsConstructor
@Getter
@Setter
public class TestTemplate {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testTemplate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<QuestionTemplate> questionTemplates = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testTemplate", fetch = FetchType.LAZY)
    private Set<Test> tests = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
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
