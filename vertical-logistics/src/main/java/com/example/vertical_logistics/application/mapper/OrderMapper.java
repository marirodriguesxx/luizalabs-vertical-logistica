package com.example.vertical_logistics.application.mapper;

import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.domain.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setDate(order.getDate());
        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setTotal(orderDTO.getTotal());
        order.setDate(orderDTO.getDate());
        return order;
    }
}
