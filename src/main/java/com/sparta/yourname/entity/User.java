package com.sparta.yourname.entity;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true) // unique: 중복 허용 여부 (false 일때 중복 허용)
    private String userId;

    @NotBlank
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String mbti;

    private String email;

    private String githuburl;

    private String blogurl;

    public User(UserRequestDto.signUp requestDto) {
        this.userId = requestDto.getUserId();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        this.username = requestDto.getUsername();
        this.specialty = requestDto.getSpecialty();
        this.mbti = requestDto.getMbti();
        this.githuburl = requestDto.getGithubUrl();
        this.blogurl = requestDto.getBlogUrl();
    }

    public UserResponseDto toUserResponseDto() {
        return UserResponseDto.builder()
                .id(id)
                .userId(userId)
                .email(email)
                .username(username)
                .specialty(specialty)
                .mbti(mbti)
                .githubUrl(githuburl)
                .blogUrl(blogurl)
                .build();
    }
}
