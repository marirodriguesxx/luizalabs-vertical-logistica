package com.example.vertical_logistics.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private int userId;
    private String name;
    private List<OrderDTO> orders;
}
