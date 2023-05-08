package com.sparta.yourname.controller;

import com.sparta.yourname.dto.BoardRequestDto;
import com.sparta.yourname.dto.CommonResponseDto;
import com.sparta.yourname.security.UserDetailsImpl;
import com.sparta.yourname.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    public CommonResponseDto<?> createBoard(@RequestBody BoardRequestDto.create requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new CommonResponseDto<>(boardService.createBoard(requestDto, userDetails));
    }

    @GetMapping("")
    public CommonResponseDto<?> showAllBoards() {
        return new CommonResponseDto<>(boardService.showAllBoards());
    }

    @DeleteMapping("")
    public CommonResponseDto<?> deleteBoard(@RequestBody BoardRequestDto.delete requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new CommonResponseDto<>(boardService.deleteBoard(requestDto, userDetails));
    }

}
