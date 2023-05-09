package com.sparta.yourname.controller;


import com.sparta.yourname.dto.CommonResponseDto;
import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
    public CommonResponseDto<?> signup(@RequestPart(value = "dto") UserRequestDto.info requestDto, @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return userService.signup(requestDto, image);
    }
    @ResponseBody
    @PostMapping("/userdelete")
    public CommonResponseDto<?> userDelete(@RequestBody UserRequestDto.info requestDto) {
        return userService.delete(requestDto);
    }
}
