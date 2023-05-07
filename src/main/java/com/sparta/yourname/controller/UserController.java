package com.sparta.yourname.controller;


import com.sparta.yourname.dto.CommonResponseDto;
import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.service.UserService;
import jakarta.servlet.http.HttpServletResponse;


import com.sparta.yourname.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public CommonResponseDto<?> login(@RequestBody UserRequestDto.login requestDto) {
        return userService.login(requestDto);
    }

    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserRequestDto.info requestDto) {
        return userService.signup(requestDto);
    }
    @PostMapping("/userdelete")
    public UserResponseDto userDelete(@RequestBody UserRequestDto.info requestDto) {
        return userService.delete(requestDto);
    }
}
