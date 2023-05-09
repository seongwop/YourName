package com.sparta.yourname.entity;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity(name = "users")
@Getter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Column(nullable = false,unique = true) // unique: 중복 허용 여부 (false 일때 중복 허용)
    private String userId;

    @Column(nullable = false)//@NotBlank
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String mbti;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String githuburl;
    @Column(nullable = false)
    private String blogurl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public User(UserRequestDto.info requestDto) {
        this.userId = requestDto.getUserId();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        this.username = requestDto.getUsername();
        this.specialty = requestDto.getSpecialty();
        this.mbti = requestDto.getMbti();
        this.githuburl = requestDto.getGithubUrl();
        this.blogurl = requestDto.getBlogUrl();
    }


    public void Update(UserRequestDto.info requestDto) {
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
