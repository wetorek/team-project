package com.wetorek.teamproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "option_templates")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OptionTemplate {
    @EqualsAndHashCode.Include
    private final UUID uuid = UUID.randomUUID();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answerText;
    private boolean correct;
    @ManyToOne
    @JoinColumn(name = "question_template_id")
    private QuestionTemplate questionTemplate;


}
