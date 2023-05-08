package com.sparta.yourname.repository;

import com.sparta.yourname.entity.Board;
import com.sparta.yourname.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByIdAndUser(Long id, User user);

}
