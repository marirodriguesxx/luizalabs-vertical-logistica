package com.example.vertical_logistics.adapter.in.web;


import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.application.mapper.UserMapper;
import com.example.vertical_logistics.application.service.UserService;
import com.example.vertical_logistics.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/with-orders-and-products")
    public ResponseEntity<List<UserDTO>> getUsersWithOrdersAndProducts() {
        List<UserDTO> usersDTO = userService.findAllUsersWithOrdersAndProducts();
        return ResponseEntity.ok(usersDTO);
    }
}
