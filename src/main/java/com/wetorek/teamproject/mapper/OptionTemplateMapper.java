package com.wetorek.teamproject.mapper;


import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.dto.OptionTemplateDtoResponse;
import com.wetorek.teamproject.entity.OptionTemplate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
