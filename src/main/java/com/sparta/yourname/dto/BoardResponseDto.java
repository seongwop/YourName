package com.sparta.yourname.dto;


import com.sparta.yourname.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private String author;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getUser().getUsername();
    }
}
