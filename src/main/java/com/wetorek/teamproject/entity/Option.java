package com.wetorek.teamproject.entity;

import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity(name = "options")
@AllArgsConstructor
@Builder
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answerText;
    private boolean marked;
    @Enumerated(EnumType.STRING)
    private OptionStatus status;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Question question;

    @ManyToOne
    @JoinColumn(name = "option_template_id")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private OptionTemplate optionTemplate;

    @PersistenceConstructor
    public Option() {
    }


}
