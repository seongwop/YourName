package com.sparta.yourname.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSummaryDto {
    private Long id;
    private String username;
    private String specialty;
    private String mbti;
    private String imageUrl;

    @Builder
    public MemberSummaryDto(Long id, String username, String specialty, String mbti, String imageUrl) {
        this.id = id;
        this.username = username;
        this.specialty = specialty;
        this.mbti = mbti;
        this.imageUrl = imageUrl;
    }
}
