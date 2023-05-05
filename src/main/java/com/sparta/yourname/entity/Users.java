package com.sparta.yourname.entity;

import com.sparta.yourname.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String mbti;

    @Column(nullable = false)
    private String githubUrl;

    @Column(nullable = false)
    private String blogUrl;






    public Users(UserRequestDto requests) {
        this.userid = requests.getUserid();
        this.password = requests.getPassword();
        this.email = requests.getEmail();
        this.username = requests.getUsername();

        this.specialty = requests.getSpecialty();
        this.mbti = requests.getMbti();
        this.githubUrl = requests.getGithubUrl();
        this.blogUrl = requests.getBlogUrl();

    }
}
