package com.example.vertical_logistics.adapter.out.persistence;

import com.example.vertical_logistics.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
}
