package com.sparta.yourname.entity;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Getter@Setter
@NoArgsConstructor

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String specialty;

    @Column(nullable = true)
    private String mbti;

    @Column(nullable = true)
    private String githuburl;

    @Column(nullable = true)
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
        this.githuburl = requestDto.getGithuburl();
        this.blogurl = requestDto.getBlogurl();
    }


    public void update(UserRequestDto.info requestDto) {
        if (requestDto.getUsername() != null) {
            this.username = requestDto.getUsername();
        }
        if (requestDto.getPassword() != null) {
            this.password = requestDto.getPassword();
        }
        if (requestDto.getEmail() != null) {
            this.email = requestDto.getEmail();
        }
        if (requestDto.getSpecialty() != null) {
            this.specialty = requestDto.getSpecialty();
        }
        if (requestDto.getMbti() != null) {
            this.mbti = requestDto.getMbti();
        }
        if (requestDto.getGithuburl() != null) {
            this.githuburl = requestDto.getGithuburl();
        }
        if (requestDto.getBlogurl() != null) {
            this.blogurl = requestDto.getBlogurl();
        }
    }


    @Setter
    @Column
    private String imageUrl;






//    public User(UserRequestDto.info requests) {
//        this.userId = requests.getUserId();
//        this.password = requests.getPassword();
//        this.email = requests.getEmail();
//        this.username = requests.getUsername();
//
//        this.specialty = requests.getSpecialty();
//        this.mbti = requests.getMbti();
//        this.githuburl = requests.getGithubUrl();
//        this.blogurl = requests.getBlogUrl();
//
//    }


    public UserResponseDto toUserResponseDto() {
        return new UserResponseDto(
                id,
                userId,
                email,
                username,
                specialty,
                mbti,
                githuburl,
                blogurl,
                imageUrl
        );
    }
}
