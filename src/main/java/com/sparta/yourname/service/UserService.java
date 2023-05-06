package com.sparta.yourname.service;


import com.sparta.yourname.domain.User;
import com.sparta.yourname.dto.CommonResponseDto;
import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    @Transactional
    public CommonResponseDto<?> login(UserRequestDto.login requestDto, HttpServletResponse response) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );




        return new CommonResponseDto<>("로그인 성공");
    }

    
    @Transactional
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        String userid = userRequestDto.getUserId();
        String password =  userRequestDto.getPassword();//passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUserId(userid);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
//
        String email = userRequestDto.getEmail();
        // 사용자 ROLE 확인
        User user = new User(userRequestDto);
        userRepository.save(user);
        return null;
    }


}
