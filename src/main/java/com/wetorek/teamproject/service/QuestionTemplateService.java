package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.entity.QuestionTemplate;
import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.exceptions.QuestionTemplateNotFound;
import com.wetorek.teamproject.repository.QuestionTemplateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionTemplateService {

    private final QuestionTemplateFactory questionTemplateFactory;
    private final TestTemplateService testTemplateService;
    private final QuestionTemplateRepository questionTemplateRepository;
    private final TestCheckerService testCheckerService;

    public Optional<QuestionTemplate> getQuestionTemplateById(int id) {
        return questionTemplateRepository.findById(id);
    }

    public List<QuestionTemplate> getQuestionTemplatesByTestId(int testId) {
        return questionTemplateRepository.getAllByTestTemplate_Id(testId);
    }

    public QuestionTemplate createNewQuestionTemplate(int testId, QuestionTemplateDtoRequest request) {
        var test = testTemplateService.getById(testId).orElseThrow(() -> new IllegalStateException("Test don't exist"));
        var questionTemplate = questionTemplateFactory.from(test, request);
        log.info("New question template created: ");
        return questionTemplateRepository.save(questionTemplate);
    }

    public QuestionTemplate updateQuestionTemplate(int questionId, int newTestId, QuestionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateRepository.findById(questionId).orElseThrow(() -> new QuestionTemplateNotFound("Question not found: " + questionId));
        if (questionTemplate.getTestTemplate().getId() == newTestId) {
            return modifyInCurrentTestTemplate(questionTemplate.getTestTemplate(), questionTemplate, request);
        } else {
            return moveQuestionTemplateToOtherTest(newTestId, questionTemplate.getTestTemplate(), questionTemplate, request);
        }
    }

    public void deleteQuestionTemplate(int questionTemplateId) {
        var questionTemplate = getQuestionTemplateById(questionTemplateId).orElseThrow(() -> new QuestionTemplateNotFound("Question not found: " + questionTemplateId));
        var test = questionTemplate.getTestTemplate();
        if (testCheckerService.existNotCheckedTest(test)) {
            throw new IllegalStateException("This test template has unchecked tests");
        }
        test.removeQuestionTemplate(questionTemplate);
        questionTemplateRepository.delete(questionTemplate);
    }

    private QuestionTemplate moveQuestionTemplateToOtherTest(final Integer newTestId, TestTemplate testTemplate, final QuestionTemplate questionTemplate, final QuestionTemplateDtoRequest request) {
        var newTestTemplate = testTemplateService.getById(newTestId).orElseThrow(() -> new QuestionTemplateNotFound("Test template not found: " + newTestId));
        if (testCheckerService.existNotCheckedTest(testTemplate) || testCheckerService.existNotCheckedTest(newTestTemplate)) {
            throw new IllegalStateException("This test template has unchecked tests");
        }
        testTemplate.removeQuestionTemplate(questionTemplate);
        questionTemplateRepository.delete(questionTemplate);
        var newQuestionTemplate = questionTemplateFactory.from(newTestTemplate, request);
        return questionTemplateRepository.save(newQuestionTemplate);
    }

    private QuestionTemplate modifyInCurrentTestTemplate(final TestTemplate testTemplate, final QuestionTemplate questionTemplate, final QuestionTemplateDtoRequest request) {
        if (testCheckerService.existNotCheckedTest(testTemplate)) {
            throw new IllegalStateException("This test template has unchecked tests: " + testTemplate.getId());
        }
        testTemplate.removeQuestionTemplate(questionTemplate);
        questionTemplateRepository.delete(questionTemplate);
        var newQuestionTemplate = questionTemplateFactory.from(testTemplate, request);
        return questionTemplateRepository.save(newQuestionTemplate);
    }
}
