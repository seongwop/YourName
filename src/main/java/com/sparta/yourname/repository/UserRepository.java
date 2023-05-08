package com.sparta.yourname.repository;


import com.sparta.yourname.entity.User;
<<<<<<< HEAD
=======

import jakarta.persistence.Table;
>>>>>>> 5485b4a9536aec3fe9f6487f8f4520649c62d6ba
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