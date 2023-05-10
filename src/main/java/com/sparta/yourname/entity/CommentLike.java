package com.sparta.yourname.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isEnable;

    public CommentLike(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public void setEnable(){
        this.isEnable = !(this.isEnable);
    }
}
