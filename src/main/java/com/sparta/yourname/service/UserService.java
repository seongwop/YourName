package com.sparta.yourname.service;

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
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        String userid = userRequestDto.getUserid();
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
