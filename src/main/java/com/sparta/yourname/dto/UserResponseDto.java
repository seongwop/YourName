package com.sparta.yourname.dto;

import lombok.Builder;
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

    @Builder
    public UserResponseDto(Long id, String userId, String email, String username, String specialty, String mbti, String githubUrl, String blogUrl) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.specialty = specialty;
        this.mbti = mbti;
        this.githubUrl = githubUrl;
        this.blogUrl = blogUrl;
    }
}

