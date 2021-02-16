package com.wetorek.teamproject.service;

import com.wetorek.teamproject.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TestFactory {

    public Test from(TestTemplate testTemplate, User assigned) {
        var test = Test.builder()
                .checked(false)
                .description(testTemplate.getDescription())
                .examinedUser(assigned)
                .name(testTemplate.getTitle())
                .testTemplate(testTemplate)
                .build();
        var questions = createQuestions(testTemplate, test);
        test.addQuestions(questions);
        return test;
    }

    public Set<Question> createQuestions(TestTemplate testTemplate, Test test) {
        return testTemplate.getQuestionTemplates().stream()
                .map(questionTemplate -> createQuestion(questionTemplate, test))
                .collect(Collectors.toSet());
    }

    private Question createQuestion(QuestionTemplate questionTemplate, Test test) {
        var question = Question.builder()
                .question(questionTemplate.getQuestion())
                .test(test)
                .maxPoints(questionTemplate.getMaxPoints())
                .questionTemplate(questionTemplate)
                .build();
        questionTemplate.addQuestion(question);
        question.addOptions(createOptions(questionTemplate, question));
        return question;
    }

    private Set<Option> createOptions(QuestionTemplate questionTemplate, Question question) {
        var allSelectedOptionTemplates = selectOptionTemplatesForQuestion(questionTemplate);
        return allSelectedOptionTemplates.stream()
                .map(optionTemplate -> from(optionTemplate, question))
                .collect(Collectors.toSet());
    }

    private Set<OptionTemplate> selectOptionTemplatesForQuestion(QuestionTemplate questionTemplate) {
        var optionTemplates = questionTemplate.getOptions();
        var allCorrectOptions = optionTemplates.stream()
                .filter(OptionTemplate::isCorrect)
                .collect(Collectors.toSet());
        var allIncorrectOptions = optionTemplates.stream()
                .filter(optionTemplate -> !optionTemplate.isCorrect())
                .collect(Collectors.toSet());
        var correctOptionsSet = createRandomSubsetOfSize(allCorrectOptions, questionTemplate.getCorrectAnswers());
        var numberOfIncorrectOptions = questionTemplate.getAllAnswers() - questionTemplate.getCorrectAnswers();
        var incorrectOptionsSet = createRandomSubsetOfSize(allIncorrectOptions, numberOfIncorrectOptions);
        var allSelectedOptionTemplates = new HashSet<>(correctOptionsSet);
        allSelectedOptionTemplates.addAll(incorrectOptionsSet);
        return allSelectedOptionTemplates;
    }

    private Set<OptionTemplate> createRandomSubsetOfSize(Set<OptionTemplate> set, int size) {
        var optionTemplateList = new ArrayList<>(set);
        Collections.shuffle(optionTemplateList);
        return optionTemplateList.stream()
                .limit(size)
                .collect(Collectors.toSet());
    }

    private Option from(OptionTemplate optionTemplate, Question question) {
        var option =  Option.builder()
                .answerText(optionTemplate.getAnswerText())
                .question(question)
                .optionTemplate(optionTemplate)
                .build();
        optionTemplate.addOption(option);
        return option;
    }
}
