package com.wetorek.teamproject.entity;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tests")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Test {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", fetch = FetchType.EAGER)
    private final Set<Question> questions = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private boolean checked;
    private boolean submitted;
    private int points;
    @ManyToOne
    @JoinColumn(name = "user_examined_id")
    private User examinedUser;
    @ManyToOne
    @JoinColumn(name = "test_template_id")
    private TestTemplate testTemplate;

    @PersistenceConstructor
    public Test() {
    }

    public void addQuestions(Set<Question> questions) {
        this.questions.addAll(questions);
    }


}
