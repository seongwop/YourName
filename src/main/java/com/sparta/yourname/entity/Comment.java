package com.sparta.yourname.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    public Comment(User user, String content, String username){
        this.user = user;
        this.content = content;
        this.username = username;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
