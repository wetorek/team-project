package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.entity.QuestionTemplate;
import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.mapper.QuestionTemplateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class QuestionTemplateFactory {
    private final QuestionTemplateMapper questionTemplateMapper;
    private final TestTemplateService testTemplateService;

    QuestionTemplate from(TestTemplate testTemplate, QuestionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateMapper.mapRequestToEntity(request);
        questionTemplate.attachParentEntities(testTemplate);
        return questionTemplate;
    }
}
