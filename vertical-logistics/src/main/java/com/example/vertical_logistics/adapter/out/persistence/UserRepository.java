package com.example.vertical_logistics.adapter.out.persistence;

import com.example.vertical_logistics.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u")
    List<User> findAllUsers();
}
