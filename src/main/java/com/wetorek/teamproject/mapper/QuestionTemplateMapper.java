package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.dto.QuestionTemplateDtoResponse;
import com.wetorek.teamproject.entity.QuestionTemplate;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {OptionTemplateMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuestionTemplateMapper {

    @Mappings({
            @Mapping(target = "optionTemplateDtoResponses", source = "options")
    })
    QuestionTemplateDtoResponse mapToDto(QuestionTemplate questionTemplate);

    List<QuestionTemplateDtoResponse> mapCollectionToDto(Collection<QuestionTemplate> questionTemplate);


    @Mappings({
    })
    Set<QuestionTemplate> mapQuestionDtoRequestSetToEntity(Collection<QuestionTemplateDtoRequest> requests);

    @Mappings({
            @Mapping(target = "options", source = "optionTemplateDto")
    })
    QuestionTemplate mapRequestToEntity(QuestionTemplateDtoRequest questionTemplateDtoRequest);
}
