package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.UserRepository;
import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);
        user.setName("John Doe");

        userDTO = new UserDTO();
        userDTO.setUserId(1);
        userDTO.setName("John Doe");
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(userDTO);

        assertNotNull(savedUser);
        assertEquals(user.getUserId(), savedUser.getUserId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1);

        assertTrue(foundUser.isPresent());
        assertEquals(user.getUserId(), foundUser.get().getUserId());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testFindAllUsersWithOrdersAndProducts() {
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAllUsers()).thenReturn(users);
        when(orderService.findOrdersByUserId(anyInt())).thenReturn(List.of(new OrderDTO()));

        List<UserDTO> userDTOs = userService.findAllUsersWithOrdersAndProducts();

        assertNotNull(userDTOs);
        assertFalse(userDTOs.isEmpty());
        assertEquals(1, userDTOs.size());
        assertEquals(user.getUserId(), userDTOs.get(0).getUserId());
        assertNotNull(userDTOs.get(0).getOrders());
        verify(userRepository, times(1)).findAllUsers();
        verify(orderService, times(1)).findOrdersByUserId(anyInt());
    }
}
