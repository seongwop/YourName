package com.sparta.yourname.dto;

import com.sparta.yourname.entity.Comment;
import com.sparta.yourname.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberResponseDto {
    private final UserResponseDto user;
    private final List<Comment> comments;

    public MemberResponseDto(User user, List<Comment> comments) {
        this.user = UserResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .specialty(user.getSpecialty())
                .mbti(user.getMbti())
                .githubUrl(user.getGithuburl())
                .blogUrl(user.getBlogurl())
                .build();
        this.comments = comments;
    }
}
