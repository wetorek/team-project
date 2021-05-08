package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.dto.TestTemplateDtoResponse;
import com.wetorek.teamproject.entity.TestTemplate;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionTemplateMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TestTemplateMapper {
    @Mappings({
            @Mapping(target = "creator", source = "user.id"),
            @Mapping(target = "questionTemplateDtoResponses", source = "questionTemplates")
    })
    TestTemplateDtoResponse mapToDto(TestTemplate testTemplate);

    List<TestTemplateDtoResponse> mapCollectionToDto(Collection<TestTemplate> testTemplates);

    @Mappings({
            @Mapping(source = "questionTemplateDto", target = "questionTemplates")
    })
    TestTemplate mapRequestToEntity(TestTemplateDtoRequest testTemplateDtoRequest);

    @AfterMapping
    default void attachEntities(@MappingTarget TestTemplate testTemplate) {
        testTemplate.getQuestionTemplates().forEach(questionTemplate -> questionTemplate.attachParentEntities(testTemplate));
    }

}
