package com.sparta.yourname.repository;


import com.sparta.yourname.entity.User;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Table(name = "users")
public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findAllByUserId(Long Id);
//    Optional<User> findByUsername(String username);
//    Optional<User> findByUserId(String userId);
    Optional<User> findByUserId(String userId);
    Optional<User> findByUsername(String userId);



}