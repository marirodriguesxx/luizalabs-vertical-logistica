package com.example.vertical_logistics.application.mapper;

import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.domain.model.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setOrders(user.getOrders().stream().map(OrderMapper::toDTO).collect(Collectors.toList()));
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setOrders(userDTO.getOrders().stream().map(OrderMapper::toEntity).collect(Collectors.toList()));
        return user;
    }
}