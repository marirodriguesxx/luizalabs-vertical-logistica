package com.example.vertical_logistics.adapter.out.persistence;

import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE p.order.id = :orderId")
    List<Product> findProductsByOrderId(@Param("orderId") Integer orderId);
}

