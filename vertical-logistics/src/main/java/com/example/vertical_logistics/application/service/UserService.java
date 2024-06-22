package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.UserRepository;
import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.application.mapper.UserMapper;
import com.example.vertical_logistics.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        return userRepository.save(user);
    }
}
