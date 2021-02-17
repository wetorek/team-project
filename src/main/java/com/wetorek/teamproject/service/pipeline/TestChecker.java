package com.wetorek.teamproject.service.pipeline;

import com.wetorek.teamproject.entity.*;
import org.springframework.stereotype.Component;

@Component
public class TestChecker implements Handler {
    @Override
    public void process(Test test) {
        test.getQuestions()
                .forEach(this::checkQuestion);
        test.setChecked(true);
    }

    private void checkQuestion(Question question) {
        question.getOptions()
                .forEach(option -> option.setStatus(checkOption(option, option.getOptionTemplate())));
    }

    private OptionStatus checkOption(Option option, OptionTemplate optionTemplate) {
        if (option.isMarked() && optionTemplate.isCorrect()) {
            return OptionStatus.MARKED_CORRECT;
        } else if (!option.isMarked() && optionTemplate.isCorrect()) {
            return OptionStatus.UNMARKED_CORRECT;
        } else if (option.isMarked() && !optionTemplate.isCorrect()) {
            return OptionStatus.MARKED_INCORRECT;
        } else {
            return OptionStatus.UNMARKED_INCORRECT;
        }
    }
}
