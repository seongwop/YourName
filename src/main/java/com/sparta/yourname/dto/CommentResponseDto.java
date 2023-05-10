package com.sparta.yourname.dto;

import com.sparta.yourname.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private String imageUrl;
    private int likeCount;

    private String userId;
    private boolean isEnabled;

    public CommentResponseDto(Comment comment, boolean isEnabled) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.imageUrl = comment.getUser().getImageUrl();
        this.likeCount = comment.getLikeCount();
        this.userId = comment.getUser().getUserId();
        this.isEnabled = isEnabled;
    }

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.imageUrl = comment.getUser().getImageUrl();
        this.likeCount = comment.getLikeCount();
        this.userId = comment.getUser().getUserId();
        this.isEnabled = false;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

