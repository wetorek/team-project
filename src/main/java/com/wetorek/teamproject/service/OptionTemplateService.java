package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.entity.OptionTemplate;
import com.wetorek.teamproject.entity.QuestionTemplate;
import com.wetorek.teamproject.repository.OptionTemplateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OptionTemplateService {
    private final OptionTemplateFactory optionTemplateFactory;
    private final OptionTemplateRepository optionTemplateRepository;
    private final TestService testService;
    private final QuestionTemplateService questionTemplateService;

    public Optional<OptionTemplate> getOptionTemplateById(final Integer optionId) {
        return optionTemplateRepository.findById(optionId);
    }

    public List<OptionTemplate> getOptionTemplatesByQuestionId(final Integer questionId) {
        return optionTemplateRepository.getAllByQuestionTemplate_Id(questionId);
    }

    public OptionTemplate createNewOptionTemplate(final Integer questionId, final OptionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateService.getQuestionTemplateById(questionId).orElseThrow(() -> new IllegalStateException("Question template doesn't exist"));
        var optionTemplate = optionTemplateFactory.from(questionTemplate, request);
        log.info("New option template created: ");
        return optionTemplateRepository.save(optionTemplate);
    }

    public void deleteOptionTemplate(final Integer optionId) {
        var optionTemplate = getOptionTemplateById(optionId).orElseThrow(() -> new IllegalStateException("Option template doesn't exist"));
        var questionTemplate = optionTemplate.getQuestionTemplate();
        var testTemplate = questionTemplate.getTestTemplate();
        if (testService.existNotCheckedTest(testTemplate)) {
            throw new IllegalStateException("This test template has unchecked tests");
        }
        questionTemplate.removeOptionTemplate(optionTemplate);
        optionTemplateRepository.delete(optionTemplate);
    }

    public OptionTemplate updateOptionTemplate(final Integer optionId, final Integer newQuestionId, final OptionTemplateDtoRequest request) {
        var currentOptionTemplate = getOptionTemplateById(optionId).orElseThrow(() -> new IllegalStateException("Option template doesn't exist"));
        var currentQuestionTemplate = currentOptionTemplate.getQuestionTemplate();
        var currentTestTemplate = currentOptionTemplate.getQuestionTemplate().getTestTemplate();
        var newQuestionTemplate = questionTemplateService.getQuestionTemplateById(newQuestionId).orElseThrow(() -> new IllegalStateException("Question template doesn't exist"));
        var newTestTemplate = newQuestionTemplate.getTestTemplate();
        if (testService.existNotCheckedTest(currentTestTemplate) || testService.existNotCheckedTest(newTestTemplate)) {
            throw new IllegalStateException("This test template has unchecked tests");
        }
        return updateOption(currentOptionTemplate, currentQuestionTemplate, newQuestionTemplate, request);
    }


    private OptionTemplate updateOption(final OptionTemplate currentOptionTemplate, final QuestionTemplate currentQuestionTemplate, final QuestionTemplate newQuestionTemplate, final OptionTemplateDtoRequest request) {
        var newOptionTemplate = optionTemplateFactory.from(newQuestionTemplate, request);
        currentQuestionTemplate.removeOptionTemplate(currentOptionTemplate);
        optionTemplateRepository.delete(currentOptionTemplate);
        return optionTemplateRepository.save(newOptionTemplate);
    }
}
