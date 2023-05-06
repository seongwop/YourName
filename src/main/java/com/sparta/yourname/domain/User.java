package com.sparta.yourname.domain;

import com.sparta.yourname.dto.UserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    public User(UserRequestDto.login requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
