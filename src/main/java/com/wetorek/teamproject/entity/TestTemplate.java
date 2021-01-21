package com.wetorek.teamproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test_templates")
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void clearAndAddAll(Set<QuestionTemplate> questionTemplateSet) {
        questionTemplates.clear();
        questionTemplates.addAll(questionTemplateSet);
    }

}
