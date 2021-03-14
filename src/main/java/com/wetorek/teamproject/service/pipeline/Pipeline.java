package com.wetorek.teamproject.service.pipeline;

import com.wetorek.teamproject.entity.Test;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class Pipeline implements Subscriber {
    private final List<Handler> handlerList;

    public Pipeline(TestChecker testChecker, PointsComputer pointsComputer, TotalPointsCalculator totalPointsCalculator) {
        this.handlerList = new LinkedList<>(Arrays.asList(testChecker, pointsComputer, totalPointsCalculator));
    }

    @Override
    public void update(Test test) {
        handlerList.forEach(handler -> handler.process(test));
    }
}
