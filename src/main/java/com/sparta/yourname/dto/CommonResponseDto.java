package com.sparta.yourname.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponseDto <T> {
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public CommonResponseDto(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public CommonResponseDto(String msg) {
        this.msg = msg;
    }
}
