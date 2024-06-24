package com.example.vertical_logistics.adapter.in.web;

import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService)).build();
    }

    @Test
    void testGetOrdersByUserId() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);

        List<OrderDTO> orders = Collections.singletonList(orderDTO);

        Mockito.when(orderService.findOrdersByUserId(anyInt())).thenReturn(orders);

        mockMvc.perform(get("/api/orders/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(1));
    }

    @Test
    void testGetOrderById() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);

        Mockito.when(orderService.findOrderById(anyInt())).thenReturn(orderDTO);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1));
    }

    @Test
    void testGetOrdersByDateRange() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);

        List<OrderDTO> orders = Collections.singletonList(orderDTO);

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 31);

        Mockito.when(orderService.findOrdersByDateRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(orders);

        mockMvc.perform(get("/api/orders/date-range")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-01-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(1));
    }
}
