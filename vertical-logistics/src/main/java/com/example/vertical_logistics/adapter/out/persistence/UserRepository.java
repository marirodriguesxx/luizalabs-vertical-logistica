package com.example.vertical_logistics.adapter.out.persistence;

import com.example.vertical_logistics.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u JOIN u.orders o WHERE o.orderId = :orderId")
    List<User> findByOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT u FROM User u JOIN u.orders o WHERE o.date BETWEEN :startDate AND :endDate")
    List<User> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
