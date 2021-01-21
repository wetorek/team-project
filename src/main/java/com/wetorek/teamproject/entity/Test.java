package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Test {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", fetch = FetchType.EAGER)
    private final Set<Question> questions = new HashSet<>();
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
