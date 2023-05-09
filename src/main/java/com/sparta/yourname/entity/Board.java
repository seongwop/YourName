package com.sparta.yourname.entity;

import com.sparta.yourname.dto.BoardRequestDto;
import com.sparta.yourname.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String title;
    @Column (nullable = false)
    private String content;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public static Board saveBoard(BoardRequestDto.create requestDto, UserDetailsImpl userDetails){
       return new Board(requestDto.getTitle(), requestDto.getContent(), userDetails.getUser());
    }
}
