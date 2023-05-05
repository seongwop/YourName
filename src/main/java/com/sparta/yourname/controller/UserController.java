package com.sparta.yourname.controller;


import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.service.UserService;
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
    @PostMapping("/signup")
    public String signup(@RequestBody UserRequestDto userRequestDto) {
        userService.signup(userRequestDto);
        return "redirect:/api/user/login-page";
    }


}
