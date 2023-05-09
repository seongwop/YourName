package com.sparta.yourname.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/* 에러 수정 */
public class UserRequestDto {

    @Getter
    @NoArgsConstructor
    public static class info {
      private String userId;
      private String password;
      private String email;
      private String username;
      private String specialty;
      private String mbti;
      private String githuburl;
      private String blogurl;

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class login {
        private String userId;
        private String password;
    }
}
