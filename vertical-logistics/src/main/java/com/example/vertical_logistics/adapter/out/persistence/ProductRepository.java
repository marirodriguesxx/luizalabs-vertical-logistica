package com.example.vertical_logistics.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.vertical_logistics.domain.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
}

