package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.OrderRepository;
import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.application.mapper.OrderMapper;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order saveOrder(OrderDTO orderDTO, User user) {
        Order order = OrderMapper.toEntity(orderDTO);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public List<OrderDTO> findOrdersByUserId(Integer userId) {
        List<OrderDTO> ordersDTO = OrderMapper.toDTOList(orderRepository.findOrdersByUserId(userId));
        List<OrderDTO> ordersResult = new ArrayList<>();

        for (OrderDTO orderDTO : ordersDTO) {
            List<ProductDTO> productsDto = productService.findProductsByOrderId(orderDTO.getOrderId());
            orderDTO.setProducts(productsDto);
            ordersResult.add(orderDTO);
        }

        return ordersResult;
    }

    public OrderDTO findOrderById(Integer orderId) {
        Optional<Order> orderOptional = orderRepository.findOrderById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            OrderDTO orderDTO = OrderMapper.toDTO(order);
            List<ProductDTO> products = productService.findProductsByOrderId(order.getOrderId());
            orderDTO.setProducts(products);
            return orderDTO;
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + orderId);
        }
    }

    public List<OrderDTO> findOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepository.findOrdersByDateRange(startDate, endDate);
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO orderDTO = OrderMapper.toDTO(order);
            List<ProductDTO> products = productService.findProductsByOrderId(order.getOrderId());
            orderDTO.setProducts(products);
            orderDTOs.add(orderDTO);
        }

        return orderDTOs;
    }
}
