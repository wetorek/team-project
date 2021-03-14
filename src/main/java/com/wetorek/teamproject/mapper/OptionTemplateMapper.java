package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.dto.OptionTemplateDtoResponse;
import com.wetorek.teamproject.entity.OptionTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OptionTemplateMapper {

    @Mappings({})
    OptionTemplateDtoResponse mapToDto(OptionTemplate optionTemplate);

    List<OptionTemplateDtoResponse> mapCollectionToDto(Collection<OptionTemplate> optionTemplates);

    OptionTemplate mapRequestToEntity(OptionTemplateDtoRequest optionTemplateDtoRequest);

    @Mappings({})
    Set<OptionTemplate> mapRequestsToEntities(Collection<OptionTemplateDtoRequest> optionTemplateDtoRequests);
}
