package com.example.vertical_logistics.application.mapper;

import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.domain.model.Product;

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
}
