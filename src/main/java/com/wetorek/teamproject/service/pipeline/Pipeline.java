package com.wetorek.teamproject.service.pipeline;

import com.wetorek.teamproject.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Pipeline implements Subscriber {
    private final List<Handler> handlerList = new ArrayList<>();

    public void addHandler(Handler handler) {
        handlerList.add(handler);
    }

    @Override
    public void update(Test test) {
        handlerList.forEach(handler -> handler.process(test));
    }

    @Autowired
    void addHandlers(TestChecker testChecker, PointsComputer pointsComputer, TotalPointsCalculator totalPointsCalculator) {
        addHandler(testChecker);
        addHandler(pointsComputer);
        addHandler(totalPointsCalculator);
//        Arrays.stream(handlers).forEach(this::addHandler);
    }
}
