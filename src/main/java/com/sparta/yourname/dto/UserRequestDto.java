package com.sparta.yourname.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    private String userid;
    private String password;
    private String email;
    private String username;
    private String specialty;

    private String mbti;
    private String githubUrl;
    private String blogUrl;

}
