package com.sparta.yourname.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class UserResponseDto {
    private Long id;
    private String userId;
    private String email;
    private String username;
    private String specialty;
    private String mbti;
    private String githubUrl;
    private String blogUrl;
}

