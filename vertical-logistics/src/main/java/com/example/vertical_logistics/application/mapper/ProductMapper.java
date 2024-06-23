package com.example.vertical_logistics.application.mapper;

import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.domain.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setValue(product.getValue());
        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setValue(productDTO.getValue());
        return product;
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Product> toEntityList(List<ProductDTO> productDTOs) {
        return productDTOs.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());
    }
}
