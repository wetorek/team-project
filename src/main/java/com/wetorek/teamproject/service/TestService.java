package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.OptionDtoRequest;
import com.wetorek.teamproject.dto.QuestionDtoRequest;
import com.wetorek.teamproject.dto.TestDtoRequest;
import com.wetorek.teamproject.entity.Question;
import com.wetorek.teamproject.entity.Test;
import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.exceptions.TemplateNotFoundEx;
import com.wetorek.teamproject.repository.TestRepository;
import com.wetorek.teamproject.service.pipeline.Subscriber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TestService {
    private final TestRepository testRepository;
    private final TestFactory testFactory;
    private final TestTemplateService testTemplateService;
    private final List<Subscriber> subscribers = new ArrayList<>();

    @Autowired
    void configureObservers(Subscriber... subscriberParams) {
        subscribers.addAll(Arrays.asList(subscriberParams));
    }

    public Test createTest(int assignedUserId, int testTemplateId) {
        var testTemplate = testTemplateService.getById(testTemplateId).orElseThrow(() -> new IllegalArgumentException("Test template not found"));
        //TODO user change
        User assignedUser = null;
        var test = testFactory.from(testTemplate, assignedUser);
        var result = testRepository.save(test);
        log.info("Test was created: " + test.getId());
        return result;
    }

    public List<Test> getAllTestsByUser(int userId) {
        return testRepository.findTestByExaminedUser_Id(userId);
    }

    public List<Test> getAllTestsByTemplate(int testTemplateId) {
        return testRepository.findTestByTestTemplate_Id(testTemplateId);
    }

    public Test submitTest(int id, TestDtoRequest testDtoRequest) {
        var test = testRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Test not found"));
        if (test.isSubmitted()) {
            throw new IllegalStateException("This test is already submited");
        }
        markAnswers(test, testDtoRequest);
        test.setSubmitted(true);
        notifySubscribers(test);
        testRepository.save(test);
        return test;
    }

    private void notifySubscribers(Test test) {
        subscribers.forEach(subscriber -> subscriber.update(test));
    }

    private void markAnswers(Test test, TestDtoRequest testDtoRequest) {
        var responses = testDtoRequest.getQuestionDtoRequests().stream()
                .map(QuestionDtoRequest::getOptions)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(OptionDtoRequest::getId, OptionDtoRequest::isMarked));
        test.getQuestions().stream()
                .map(Question::getOptions)
                .flatMap(Collection::stream)
                .forEach(option -> option.setMarked(responses.get(option.getId())));
    }

    public Optional<Test> getTestById(int id) {
        return testRepository.findById(id);
    }
}
