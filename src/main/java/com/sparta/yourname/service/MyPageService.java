package com.sparta.yourname.service;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {
    private UserRepository userRepository;

    public UserResponseDto getInformation(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(
                ()-> new IllegalArgumentException("User not found")
        );
        return user.toUserResponseDto();
    }

    public UserResponseDto updateInformation(String userId, UserRequestDto updatedInfo) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 업데이트
//        user.setEmail(updatedInfo.getEmail());
//        user.setSpecialty(updatedInfo.getSpecialty());
//        user.setMbti(updatedInfo.getMbti());
//        user.setGithuburl(updatedInfo.getGithubUrl());
//        user.setBlogurl(updatedInfo.getBlogUrl());

        // 업데이트한 정보를 저장
        User updatedUser = userRepository.save(user);

        return updatedUser.toUserResponseDto();
    }



}
