package com.sparta.yourname.repository;

import com.sparta.yourname.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//        List<User> findAllByUserId(Long Id);
//    Optional<Users> findByUsername(String username);
//    Optional<User> findByUserId(String userId);
    Optional<User> findByUserId(String userid);

}