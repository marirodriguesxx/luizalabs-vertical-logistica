package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.ProductRepository;
import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.application.mapper.ProductMapper;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(ProductDTO productDTO, Order order) {
        Product product = ProductMapper.toEntity(productDTO);
        product.setOrder(order);
        return productRepository.save(product);
    }
}
