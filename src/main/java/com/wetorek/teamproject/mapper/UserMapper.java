package com.wetorek.teamproject.mapper;

import com.wetorek.teamproject.dto.UserResponseDto;
import com.wetorek.teamproject.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapToDto(User user);

    List<UserResponseDto> mapListToDto(List<User> users);
}
