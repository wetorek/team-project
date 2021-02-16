package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.TestDtoResponse;
import com.wetorek.teamproject.entity.Test;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestMapper {
    private final ModelMapper modelMapper;
    private final QuestionMapper questionMapper;

    public TestDtoResponse mapToResponse(Test test) {
        var response = modelMapper.map(test, TestDtoResponse.class);
        //TODO change this
//        response.setExaminedUserId(test.getExaminedUser().getId());
        response.setExaminedUserId(test.getId());
        response.setTestTemplateId(test.getTestTemplate().getId());
        var mappedQuestions = questionMapper.mapSetToDto(test.getQuestions());
        response.setQuestionDtoResponseList(mappedQuestions);
        return response;
    }
}
