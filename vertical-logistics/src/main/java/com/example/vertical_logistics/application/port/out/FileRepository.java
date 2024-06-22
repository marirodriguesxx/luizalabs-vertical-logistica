package com.example.vertical_logistics.application.port.out;

import com.example.vertical_logistics.domain.model.User;

import java.time.LocalDate;
import java.util.List;

public interface FileRepository {
    void saveAll(List<User> users);
    List<User> findAll();
    List<User> findByOrderId(Integer orderId);
    List<User> findByDateRange(LocalDate startDate, LocalDate endDate);
}
