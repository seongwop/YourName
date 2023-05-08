package com.sparta.yourname.entity;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String mbti;

    @Column(nullable = false)
    private String githuburl;

    @Column(nullable = false)
    private String blogurl;






    public User(UserRequestDto.info requests) {
        this.userId = requests.getUserId();
        this.password = requests.getPassword();
        this.email = requests.getEmail();
        this.username = requests.getUsername();

        this.specialty = requests.getSpecialty();
        this.mbti = requests.getMbti();
        this.githuburl = requests.getGithuburl();
        this.blogurl = requests.getBlogurl();

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
        return new UserResponseDto(
                id,
                userId,
                email,
                username,
                specialty,
                mbti,
                githuburl,
                blogurl
        );
    }
}
