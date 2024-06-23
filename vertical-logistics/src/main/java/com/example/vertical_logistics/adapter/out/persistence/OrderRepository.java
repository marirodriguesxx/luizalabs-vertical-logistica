package com.example.vertical_logistics.adapter.out.persistence;

import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Integer userId);
}
