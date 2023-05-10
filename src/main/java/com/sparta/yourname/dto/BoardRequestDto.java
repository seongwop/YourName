package com.sparta.yourname.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


public class BoardRequestDto {

    @Getter
    @NoArgsConstructor
    public static class create{
        private String title;
        private String content;
    }
}
