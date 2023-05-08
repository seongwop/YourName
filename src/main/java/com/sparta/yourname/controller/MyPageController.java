package com.sparta.yourname.controller;


import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class MyPageController {

    private final MyPageService myPageService;

    //개인 정보 조회
    @GetMapping("/api/mypage")
    public ResponseEntity<UserResponseDto> getPersonalInformation(@RequestParam String userId) {
        UserResponseDto user = myPageService.getInformation(userId);
        return ResponseEntity.ok(user);
    }

    //개인 정보 수정
    @PutMapping("/api/mypage")
    public ResponseEntity<UserResponseDto> updatePersonalInformation(@RequestParam String userId, @RequestBody UserRequestDto updatedInfo) {
        UserResponseDto updatedUser = myPageService.updateInformation(userId, updatedInfo);
        return ResponseEntity.ok(updatedUser);
    }



}
