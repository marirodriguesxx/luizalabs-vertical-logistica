package com.example.vertical_logistics.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private int productId;
    private BigDecimal value;
}
