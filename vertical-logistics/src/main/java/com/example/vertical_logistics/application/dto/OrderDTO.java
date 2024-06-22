package com.example.vertical_logistics.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private int orderId;
    private BigDecimal total;
    private LocalDate date;
    private List<ProductDTO> products;
}
