package com.example.vertical_logistics.application.mapper;

import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.domain.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        return user;
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<User> toEntityList(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }
}
