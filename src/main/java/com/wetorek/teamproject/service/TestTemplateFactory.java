package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.mapper.TestTemplateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TestTemplateFactory {
    private final TestTemplateMapper testTemplateMapper;


    public TestTemplate from(final TestTemplateDtoRequest request) {
        var testTemplate = testTemplateMapper.mapRequestToEntity(request);
        //changes here associated with user
        return testTemplate;
    }
}
