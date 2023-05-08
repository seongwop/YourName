package com.sparta.yourname.service;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.repository.UserRepository;
import com.sparta.yourname.security.authentication.AuthenticationFacade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    public UserResponseDto getInformation() {

        String userId = authenticationFacade.getAuthentication().getName();
        System.out.println(userId);
        User user = userRepository.findByUsername(userId).orElseThrow(
                ()-> new IllegalArgumentException("User not found")
        );
        return user.toUserResponseDto();
    }

    public UserResponseDto updateInformation(UserRequestDto.info updatedInfo) {
        String userId = authenticationFacade.getAuthentication().getName();

        User user = userRepository.findByUsername(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 업데이트
        user.Update(updatedInfo);
        // 업데이트한 정보를 저장
        User updatedUser = userRepository.save(user);

        return updatedUser.toUserResponseDto();
    }
}
