package com.sparta.yourname.controller;


import com.sparta.yourname.dto.CommonResponseDto;
import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.service.UserService;
import jakarta.servlet.http.HttpServletResponse;


import com.sparta.yourname.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public CommonResponseDto<?> login(@RequestBody UserRequestDto.login requestDto, HttpServletResponse response) {
        return userService.login(requestDto, response);
    }
    @ResponseBody
    @PostMapping("/signup")
    public CommonResponseDto<?> signup(@RequestBody UserRequestDto.info requestDto) {
        return userService.signup(requestDto);
    }
    @ResponseBody
    @PostMapping("/userdelete")
    public CommonResponseDto<?> userDelete(@RequestBody UserRequestDto.info requestDto) {
        System.out.println("깃 테스트");
        return userService.delete(requestDto);
    }

}
