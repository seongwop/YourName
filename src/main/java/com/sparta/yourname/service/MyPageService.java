package com.sparta.yourname.service;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.repository.UserRepository;
import com.sparta.yourname.security.UserDetailsImpl;


import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;


    public UserResponseDto getInformation(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("User not found")
        );
        return user.toUserResponseDto();
    }

    public UserResponseDto updateInformation(UserRequestDto.info updatedInfo, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 업데이트
        user.Update(updatedInfo);
        // 업데이트한 정보를 저장
        User updatedUser = userRepository.save(user);

        return updatedUser.toUserResponseDto();
    }
}
