package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.dto.OptionTemplateDtoResponse;
import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.dto.QuestionTemplateDtoResponse;
import com.wetorek.teamproject.entity.OptionTemplate;
import com.wetorek.teamproject.entity.QuestionTemplate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionTemplateMapper {
    private final ModelMapper modelMapper;
    private final OptionTemplateMapper optionTemplateMapper;

    public QuestionTemplate mapRequestToEntity(QuestionTemplateDtoRequest request) {
        var questionTemplate = modelMapper.map(request, QuestionTemplate.class);
        questionTemplate.setOptions(mapOptionDtoRequestSetToEntity(request.getOptionTemplateDto()));
        return questionTemplate;
    }

    public QuestionTemplateDtoResponse mapEntityToResponse(QuestionTemplate entity) {
        var questionResponse = modelMapper.map(entity, QuestionTemplateDtoResponse.class);
        questionResponse.setOptionTemplateDto(mapOptionEntitySetToResponses(entity.getOptions()));
        return questionResponse;
    }

    private Set<OptionTemplate> mapOptionDtoRequestSetToEntity(Set<OptionTemplateDtoRequest> requests) {
        return requests.stream()
                .map(optionTemplateMapper::mapRequestToEntity)
                .collect(Collectors.toSet());
    }

    private Set<OptionTemplateDtoResponse> mapOptionEntitySetToResponses(Set<OptionTemplate> optionTemplateSet) {
        return optionTemplateSet.stream()
                .map(optionTemplateMapper::mapEntityToResponse)
                .collect(Collectors.toSet());
    }
}
