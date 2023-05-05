package com.sparta.yourname.repository;

import com.sparta.yourname.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
        List<Users> findAllByUserId(Long Id);
//    Optional<Users> findByUsername(String username);
    Optional<Users> findByUserId(String userId);

}