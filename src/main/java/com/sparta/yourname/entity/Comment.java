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
    public Comment(User user, String content){
        this.user = user;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }



}
