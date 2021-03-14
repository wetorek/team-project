package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.QuestionDtoResponse;
import com.wetorek.teamproject.entity.Question;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {OptionMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuestionMapper {
    @Mappings({
            @Mapping(target = "options", source = "options"),
            @Mapping(target = "testId", source = "test.id")
    })
    QuestionDtoResponse mapToDto(Question question);


    @Mappings({})
    List<QuestionDtoResponse> mapCollectionToDto(Collection<Question> questionSet);
}
