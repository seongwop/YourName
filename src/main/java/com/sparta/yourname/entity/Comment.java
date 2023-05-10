package com.sparta.yourname.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@DynamicInsert
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

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likeCount;


    public Comment(User user, String content, String username){
        this.user = user;
        this.content = content;
        this.username = username;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateLike (Boolean likeOrDislike){
        this.likeCount = Boolean.TRUE.equals(likeOrDislike) ? this.likeCount + 1 : this.likeCount - 1;
    }


}
