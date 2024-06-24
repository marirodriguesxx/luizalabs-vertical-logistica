package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.OrderRepository;
import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.application.mapper.OrderMapper;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderDTO orderDTO;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);
        user.setName("John Doe");

        order = new Order();
        order.setOrderId(1);
        order.setTotal(BigDecimal.valueOf(100.00));
        order.setDate(LocalDate.now());
        order.setUser(user);

        orderDTO = OrderMapper.toDTO(order);
    }

    @Test
    void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.saveOrder(orderDTO, user);

        assertNotNull(savedOrder);
        assertEquals(order.getOrderId(), savedOrder.getOrderId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetOrderById() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1);

        assertTrue(foundOrder.isPresent());
        assertEquals(order.getOrderId(), foundOrder.get().getOrderId());
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void testFindOrdersByUserId() {
        List<Order> orders = Collections.singletonList(order);
        when(orderRepository.findOrdersByUserId(anyInt())).thenReturn(orders);
        when(productService.findProductsByOrderId(anyInt())).thenReturn(List.of(new ProductDTO()));

        List<OrderDTO> orderDTOs = orderService.findOrdersByUserId(1);

        assertNotNull(orderDTOs);
        assertFalse(orderDTOs.isEmpty());
        assertEquals(1, orderDTOs.size());
        assertEquals(order.getOrderId(), orderDTOs.get(0).getOrderId());
        verify(orderRepository, times(1)).findOrdersByUserId(1);
        verify(productService, times(1)).findProductsByOrderId(anyInt());
    }

    @Test
    void testFindOrderById() {
        when(orderRepository.findOrderById(anyInt())).thenReturn(Optional.of(order));
        when(productService.findProductsByOrderId(anyInt())).thenReturn(List.of(new ProductDTO()));

        OrderDTO foundOrderDTO = orderService.findOrderById(1);

        assertNotNull(foundOrderDTO);
        assertEquals(order.getOrderId(), foundOrderDTO.getOrderId());
        verify(orderRepository, times(1)).findOrderById(1);
        verify(productService, times(1)).findProductsByOrderId(anyInt());
    }

    @Test
    void testFindOrdersByDateRange() {
        List<Order> orders = Collections.singletonList(order);
        when(orderRepository.findOrdersByDateRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(orders);
        when(productService.findProductsByOrderId(anyInt())).thenReturn(List.of(new ProductDTO()));

        List<OrderDTO> orderDTOs = orderService.findOrdersByDateRange(LocalDate.now().minusDays(1), LocalDate.now());

        assertNotNull(orderDTOs);
        assertFalse(orderDTOs.isEmpty());
        assertEquals(1, orderDTOs.size());
        assertEquals(order.getOrderId(), orderDTOs.get(0).getOrderId());
        verify(orderRepository, times(1)).findOrdersByDateRange(any(LocalDate.class), any(LocalDate.class));
        verify(productService, times(1)).findProductsByOrderId(anyInt());
    }
}
