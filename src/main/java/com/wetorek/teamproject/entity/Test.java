package com.wetorek.teamproject.entity;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "tests")
@AllArgsConstructor
@Data
@Builder
public class Test {
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User examinedUser;

    @ManyToOne
    @JoinColumn(name = "test_template_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TestTemplate testTemplate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", fetch = FetchType.EAGER)
    private Set<Question> questions;

    @PersistenceConstructor
    public Test() {
    }
}
