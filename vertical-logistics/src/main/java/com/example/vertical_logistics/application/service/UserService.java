package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.UserRepository;
import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.application.mapper.OrderMapper;
import com.example.vertical_logistics.application.mapper.UserMapper;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private OrderService orderService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<UserDTO> findAllUsersWithOrdersAndProducts() {
        List<UserDTO> usersDTO = UserMapper.toDTOList(userRepository.findAllUsers());
        List<UserDTO> usersResult = new ArrayList<>();

        for (UserDTO userDTO : usersDTO) {
            List<OrderDTO> ordersDTO = orderService.findOrdersByUserId(userDTO.getUserId());
            userDTO.setOrders(ordersDTO);
            usersResult.add(userDTO);
        }

        return usersResult;
    }
}
