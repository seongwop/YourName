package com.sparta.yourname.controller;


import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
/*GIT TEST*/

public class MyPageController {

    private final MyPageService myPageService;


    @GetMapping
    public ResponseEntity<UserResponseDto> getPersonalInformation() {
        UserResponseDto user = myPageService.getInformation();
        return ResponseEntity.ok(user);
    }

    // 개인 정보 수정
    @PutMapping
    public ResponseEntity<UserResponseDto> updatePersonalInformation(@RequestBody UserRequestDto.info updatedInfo) {
        UserResponseDto updatedUser = myPageService.updateInformation(updatedInfo);
        return ResponseEntity.ok(updatedUser);
    }



}
