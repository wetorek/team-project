package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.OptionDtoResponse;
import com.wetorek.teamproject.entity.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    @Mappings({
            @Mapping(target = "optionStatus", source = "status")
    })
    OptionDtoResponse mapToDto(Option option);

    @Mappings({})
    List<OptionDtoResponse> mapCollectionToDto(Collection<Option> optionSet);
}
