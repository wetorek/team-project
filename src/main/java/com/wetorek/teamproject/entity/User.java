package com.wetorek.teamproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;


    //authorities
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<TestTemplate> testTemplates = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinedUser", fetch = FetchType.EAGER)
    private Set<Test> tests = new HashSet<>();

}