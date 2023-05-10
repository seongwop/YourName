package com.sparta.yourname.util;

import org.springframework.http.HttpStatus;

public enum CustomErrorMessage {
    //Error Messages
    WRONG_ID_FORMAT("잘못된 사용자명입니다. 사용자명은 4자에서 10자 사이의 소문자와 숫자로만 구성되어야 합니다.", HttpStatus.NOT_ACCEPTABLE),
    WRONG_PASSWORD_FORMAT("잘못된 비밀번호입니다. 비밀번호는 8자에서 15자 사이여야 하며, 적어도 하나의 대문자, 소문자, 숫자, 특수 문자를 포함해야 합니다.", HttpStatus.NOT_ACCEPTABLE),
    USER_EXISTS("중복된 사용자가 존재합니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST("사용자가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    ID_NOT_EXIST("아이디가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    BOARD_NOT_EXIST_OR_WRONG_USER("게시글이 존재하지 않거나 작성자가 아닙니다.",HttpStatus.NOT_FOUND ),
    COMMENT_NOT_EXIST("댓글이 존재하지 않습니다.",HttpStatus.NOT_FOUND),
    WRONG_USER("작성자가 아닙니다", HttpStatus.BAD_REQUEST);


    private final String message;
    private final HttpStatus status;

    CustomErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
