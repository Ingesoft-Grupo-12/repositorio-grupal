package com.union.unionbackend.mapper;

import com.union.unionbackend.dtos.UserDto;
import com.union.unionbackend.models.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto userToDto(User user);

  User dtoToUser(UserDto userDto);

  List<UserDto> usersToUserDtos(List<User> users);
}
