package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.ProductRepository;
import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.application.mapper.ProductMapper;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(ProductDTO productDTO, Order order) {
        Product product = ProductMapper.toEntity(productDTO);
        product.setOrder(order);
        productRepository.save(product);
    }

    public List<ProductDTO> findProductsByOrderId(Integer orderId) {
        return ProductMapper.toDTOList(productRepository.findProductsByOrderId(orderId));
    }
}
