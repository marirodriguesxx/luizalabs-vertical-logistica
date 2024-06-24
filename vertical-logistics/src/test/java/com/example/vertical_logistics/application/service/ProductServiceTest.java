package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.ProductRepository;
import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDTO productDTO;
    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order();
        order.setOrderId(1);

        product = new Product();
        product.setProductId(1);
        product.setValue(new BigDecimal("100.00"));
        product.setOrder(order);

        productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setValue(new BigDecimal("100.00"));
    }

    @Test
    public void testSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.saveProduct(productDTO, order);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testFindProductsByOrderId() {
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setValue(new BigDecimal("100.00"));

        Product product2 = new Product();
        product2.setProductId(2);
        product2.setValue(new BigDecimal("200.00"));

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findProductsByOrderId(1)).thenReturn(products);

        List<ProductDTO> productDTOs = productService.findProductsByOrderId(1);

        assertEquals(2, productDTOs.size());
        assertEquals(1, productDTOs.get(0).getProductId());
        assertEquals(new BigDecimal("100.00"), productDTOs.get(0).getValue());
        assertEquals(2, productDTOs.get(1).getProductId());
        assertEquals(new BigDecimal("200.00"), productDTOs.get(1).getValue());

        verify(productRepository, times(1)).findProductsByOrderId(1);
    }
}
