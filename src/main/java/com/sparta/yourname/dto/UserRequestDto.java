package com.sparta.yourname.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {
    @Getter
    @NoArgsConstructor
    public static class signUp {

    }

    @Getter
    @NoArgsConstructor
    public static class login {
        private String username;
        private String password;
    }

}
