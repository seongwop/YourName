package com.sparta.yourname.dto;

import lombok.Builder;

public class MemberSummaryDto {
    private Long id;
    private String username;
    private String specialty;
    private String mbti;

    @Builder
    public MemberSummaryDto(Long id, String username, String specialty, String mbti) {
        this.id = id;
        this.username = username;
        this.specialty = specialty;
        this.mbti = mbti;
    }
}
