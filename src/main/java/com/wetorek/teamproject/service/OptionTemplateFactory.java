package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.entity.OptionTemplate;
import com.wetorek.teamproject.entity.QuestionTemplate;
import com.wetorek.teamproject.mapper.OptionTemplateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class OptionTemplateFactory {
    private final OptionTemplateMapper optionTemplateMapper;

    public OptionTemplate from(QuestionTemplate questionTemplate, OptionTemplateDtoRequest request) {
        var optionTemplate = optionTemplateMapper.mapRequestToEntity(request);
        optionTemplate.setQuestionTemplate(questionTemplate);
        return optionTemplate;
    }
}
