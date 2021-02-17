package com.wetorek.teamproject.service.pipeline;

import com.wetorek.teamproject.entity.Question;
import com.wetorek.teamproject.entity.Test;
import org.springframework.stereotype.Component;

@Component
public class TotalPointsCalculator implements Handler {
    @Override
    public void process(Test test) {
        var sumOfPoints = test.getQuestions().stream()
                .mapToInt(Question::getPoints)
                .sum();
        test.setPoints(sumOfPoints);
    }
}
