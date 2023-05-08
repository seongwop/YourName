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

import static com.sparta.yourname.util.ValidateUtil.isValidPassword;
import static com.sparta.yourname.util.ValidateUtil.isValidUsername;

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

        if (!passwordEncoder.matches(requestDto.getPassword(),user.getPassword())) {
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
    public CommonResponseDto<?> signup(UserRequestDto.info userRequestDto) {

        String userId = userRequestDto.getUserId();
        String password = userRequestDto.getPassword();
        // username과 password의 유효성 검사
        boolean isUsernameValid = isValidUsername(userId);
        boolean isPasswordValid = isValidPassword(password);

        // 유효성 검사 결과 출력
        System.out.println("Username is valid: " + isUsernameValid);
        System.out.println("Password is valid: " + isPasswordValid);
        password = passwordEncoder.encode(password);
        userRequestDto.setPassword(password);
        // 회원 중복 확인
        try {
            Optional<User> found = userRepository.findByUserId(userId);
            if (found.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            }
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException("사용자 조회 중 오류가 발생했습니다.");
        }


        User user = new User(userRequestDto);
        userRepository.save(user);
        return new CommonResponseDto<>("회원 가입 성공");
    }

    @Transactional
    public CommonResponseDto<?> delete(UserRequestDto.info userRequestDto) {

        userRepository.findByUserId(userRequestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        User user = userRepository.findByUserId(userRequestDto.getUserId()).orElseGet(User::new);

        // 해당 사용자와 관련된 refresh_token 레코드 삭제
        refreshTokenRepository.deleteByUser(user);

        userRepository.delete(user);
        return new CommonResponseDto<>("회원 탈퇴 성공");
    }
}
