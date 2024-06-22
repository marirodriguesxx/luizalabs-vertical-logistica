package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.OrderRepository;
import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.mapper.OrderMapper;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(OrderDTO orderDTO, User user) {
        Order order = OrderMapper.toEntity(orderDTO);
        order.setUser(user);
        return orderRepository.save(order);
    }
}
