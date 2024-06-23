package com.example.vertical_logistics.adapter.out.persistence;

import com.example.vertical_logistics.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Integer userId);

    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId")
    Optional<Order> findOrderById(@Param("orderId") Integer orderId);

    @Query("SELECT o FROM Order o WHERE o.date BETWEEN :startDate AND :endDate")
    List<Order> findOrdersByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
