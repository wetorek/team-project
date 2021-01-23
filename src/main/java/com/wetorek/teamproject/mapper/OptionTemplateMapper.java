package com.wetorek.teamproject.mapper;


import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.dto.OptionTemplateDtoResponse;
import com.wetorek.teamproject.entity.OptionTemplate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OptionTemplateMapper {
    private final ModelMapper modelMapper;

    public OptionTemplate mapRequestToEntity(OptionTemplateDtoRequest request) {
        return modelMapper.map(request, OptionTemplate.class);
    }

    public OptionTemplateDtoResponse mapEntityToResponse(OptionTemplate entity) {
        return modelMapper.map(entity, OptionTemplateDtoResponse.class);
    }

    public List<OptionTemplateDtoResponse> mapListOfEntityToDto(List<OptionTemplate> optionTemplateList) {
        return optionTemplateList.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
