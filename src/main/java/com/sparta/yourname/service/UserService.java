package com.sparta.yourname.service;

import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.entity.Users;
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
    public void signup(UserRequestDto userRequestDto) {
        String userid = userRequestDto.getUserid();
        String password =  userRequestDto.getPassword();//passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<Users> found = userRepository.findByUserId(userid);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        String email = userRequestDto.getEmail();
        // 사용자 ROLE 확인
        Users user = new Users(userRequestDto);
        userRepository.save(user);
    }

}
