package com.wetorek.teamproject.service.pipeline;

import com.wetorek.teamproject.entity.Option;
import com.wetorek.teamproject.entity.OptionStatus;
import com.wetorek.teamproject.entity.Question;
import com.wetorek.teamproject.entity.Test;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class PointsComputer implements Handler {
    @Override
    public void process(Test test) {
        test.getQuestions().forEach(
                question -> question.setPoints(calculatePoints(question))
        );
    }

    private int calculatePoints(Question question) {
        var maxPoints = new BigDecimal(question.getMaxPoints());
        var allAnswers = new BigDecimal(question.getQuestionTemplate().getAllAnswers());
        var correctAnswers = new BigDecimal(question.getQuestionTemplate().getCorrectAnswers());
        var incorrectAnswers = allAnswers.subtract(correctAnswers);
        var correctAnswerValue = maxPoints.divide(correctAnswers, 2, RoundingMode.HALF_UP);
        var incorrectAnswerValue = maxPoints.divide(incorrectAnswers, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
        var totalPoints = question.getOptions()
                .stream()
                .map(Option::getStatus)
                .map(optionStatus -> mapStatusToPoints(optionStatus, correctAnswerValue, incorrectAnswerValue))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPoints.toBigInteger().intValueExact();
    }

    private BigDecimal mapStatusToPoints(OptionStatus optionStatus, BigDecimal correctAnswerValue, BigDecimal incorrectAnswerValue) {
        if (optionStatus == OptionStatus.MARKED_CORRECT) {
            return correctAnswerValue;
        }
        if (optionStatus == OptionStatus.MARKED_INCORRECT || optionStatus == OptionStatus.UNMARKED_CORRECT) {
            return incorrectAnswerValue;
        }
        return BigDecimal.ZERO;
    }


}
