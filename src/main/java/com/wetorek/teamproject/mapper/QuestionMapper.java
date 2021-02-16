package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.QuestionDtoResponse;
import com.wetorek.teamproject.entity.Question;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionMapper {
    private final ModelMapper modelMapper;
    private final OptionMapper optionMapper;

    QuestionDtoResponse mapToDto(Question question) {
        var response = modelMapper.map(question, QuestionDtoResponse.class);
        response.setTestId(question.getTest().getId());
        var mappedOptions = optionMapper.mapSetToDto(question.getOptions());
        response.setOptions(mappedOptions);
        return response;
    }

    List<QuestionDtoResponse> mapSetToDto(Set<Question> questionSet) {
        return questionSet.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
