package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.OptionDtoResponse;
import com.wetorek.teamproject.entity.Option;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OptionMapper {
    private final ModelMapper mapper;

    public OptionDtoResponse mapToDto(Option option) {
        return mapper.map(option, OptionDtoResponse.class);
    }

    public List<OptionDtoResponse> mapSetToDto(Set<Option> optionSet) {
        return optionSet.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
