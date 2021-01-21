package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.dto.QuestionTemplateDtoResponse;
import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.dto.TestTemplateDtoResponse;
import com.wetorek.teamproject.entity.QuestionTemplate;
import com.wetorek.teamproject.entity.TestTemplate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TestTemplateMapper {
    private final ModelMapper modelMapper;
    private final QuestionTemplateMapper questionTemplateMapper;


    public TestTemplate mapRequestToEntity(TestTemplateDtoRequest request) {
        var test = modelMapper.map(request, TestTemplate.class);
        test.setQuestionTemplates(mapQuestionDtoRequestSetToEntity(request.getQuestionTemplateDto()));
        test.getQuestionTemplates().forEach(questionTemplate -> questionTemplate.attachParentEntities(test));
        return test;
    }


    public TestTemplateDtoResponse mapEntityToResponse(TestTemplate testTemplate) {
        var testResponse = modelMapper.map(testTemplate, TestTemplateDtoResponse.class);
        testResponse.setQuestionTemplateDto(mapQuestionEntitySetToResponses(testTemplate.getQuestionTemplates()));
        return testResponse;
    }


    public List<TestTemplateDtoResponse> mapListOfEntitiesToResponses(List<TestTemplate> testTemplateList) {
        return testTemplateList.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private Set<QuestionTemplate> mapQuestionDtoRequestSetToEntity(Set<QuestionTemplateDtoRequest> requests) {
        return requests.stream()
                .map(questionTemplateMapper::mapRequestToEntity)
                .collect(Collectors.toSet());
    }

    private Set<QuestionTemplateDtoResponse> mapQuestionEntitySetToResponses(Set<QuestionTemplate> questionTemplates) {
        return questionTemplates.stream()
                .map(questionTemplateMapper::mapEntityToResponse)
                .collect(Collectors.toSet());
    }


}
