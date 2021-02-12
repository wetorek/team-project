package com.wetorek.teamproject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "test_templates")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TestTemplate {
    @EqualsAndHashCode.Include
    private final UUID uuid = UUID.randomUUID();
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

    public void removeQuestionTemplate(QuestionTemplate questionTemplate) {
        questionTemplates.remove(questionTemplate);
    }

}
