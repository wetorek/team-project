package com.wetorek.teamproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Test {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", fetch = FetchType.EAGER)
    private final Set<Question> questions = new HashSet<>();
    @EqualsAndHashCode.Include
    private final UUID uuid = UUID.randomUUID();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private boolean checked;
    @ManyToOne
    @JoinColumn(name = "user_examined_id")
    private User examinedUser;

    @ManyToOne
    @JoinColumn(name = "test_template_id")
    private TestTemplate testTemplate;

}
