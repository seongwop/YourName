package com.sparta.yourname.util;

public enum CustomErrorMessage {
    //Error Messages
    USER_EXISTS("중복된 사용자가 존재합니다."),
    SEARCHING_USER_ERROR("사용자 조회 중 오류가 발생했습니다."),
    USER_NOT_EXIST("사용자가 존재하지 않습니다."),
    ID_NOT_EXIST("아이디가 존재하지 않습니다."),
    WRONG_PASSWORD("비밀번호가 일치하지 않습니다."),
    BOARD_NOT_EXIST_OR_WRONG_USER("게시글이 존재하지 않거나 작성자가 아닙니다."),
    COMMENT_NOT_EXIST("댓글이 존재하지 않습니다."),
    WRONG_USER("작성자가 아닙니다");


    private final String message;

    CustomErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
