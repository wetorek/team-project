package com.wetorek.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity(name = "options")
@AllArgsConstructor
@Builder
@Getter
@Setter
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
    private Question question;

    @ManyToOne
    @JoinColumn(name = "option_template_id")
    private OptionTemplate optionTemplate;

    @PersistenceConstructor
    public Option() {

    }


}
