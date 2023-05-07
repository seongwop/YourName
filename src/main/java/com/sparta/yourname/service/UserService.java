package com.sparta.yourname.service;

import com.sparta.yourname.dto.CommonResponseDto;
import com.sparta.yourname.dto.TokenDto;
import com.sparta.yourname.dto.UserRequestDto;
import com.sparta.yourname.entity.RefreshToken;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.jwt.JwtUtil;
import com.sparta.yourname.repository.RefreshTokenRepository;
import com.sparta.yourname.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.yourname.dto.UserResponseDto;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public CommonResponseDto<?> login(UserRequestDto.login requestDto, HttpServletResponse response) {
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!passwordEncoder.matches(user.getPassword(), requestDto.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다."); // 디테일한 예외 클래스 필요
        }
        TokenDto tokenDto = jwtUtil.createAllToken(user.getUserId());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUser(user);

        // refreshToken이 존재할 경우 토큰 업데이트
        if (refreshToken.isPresent()) {
            refreshToken.get().updateToken(tokenDto.getRefreshToken());
        } else { // 존재하지 않을 경우 새로 발급
            RefreshToken newToken = RefreshToken.saveToken(tokenDto.getRefreshToken(), user);
            refreshTokenRepository.save(newToken);
        }

        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());

        return new CommonResponseDto<>("로그인 성공");
    }

    @Transactional
    public UserResponseDto signup(UserRequestDto.signUp userRequestDto) {
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
