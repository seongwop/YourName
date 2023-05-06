package com.sparta.yourname.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserRequestDto {
    @Getter
    @NoArgsConstructor
    public static class signUp {
      private String userId;
      private String password;
      private String email;
      private String username;
      private String specialty;
      private String mbti;
      private String githubUrl;
      private String blogUrl;
    }

    @Getter
    @NoArgsConstructor
    public static class login {
        private String userId;
        private String password;
    }
}
