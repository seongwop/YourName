package com.sparta.yourname.repository;

import com.sparta.yourname.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
