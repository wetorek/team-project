package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.TestDtoResponse;
import com.wetorek.teamproject.entity.Test;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TestMapper {

    @Mappings({
            @Mapping(target = "examinedUserId", source = "examinedUser.id"),
            @Mapping(target = "examinedUserUsername", source = "examinedUser.username"),
            @Mapping(target = "testTemplateId", source = "testTemplate.id"),
            @Mapping(target = "questionDtoResponseList", source = "questions")
    })
    TestDtoResponse mapToDto(Test test);

    List<TestDtoResponse> mapListToDto(List<Test> tests);
}
