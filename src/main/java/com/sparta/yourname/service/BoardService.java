package com.sparta.yourname.service;

import com.sparta.yourname.dto.BoardRequestDto;
import com.sparta.yourname.dto.BoardResponseDto;
import com.sparta.yourname.entity.Board;
import com.sparta.yourname.repository.BoardRepository;
import com.sparta.yourname.repository.UserRepository;
import com.sparta.yourname.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto.create requestDto,UserDetailsImpl userDetails) {
        Board board = boardRepository.saveAndFlush(Board.saveBoard(requestDto, userDetails));
        return new BoardResponseDto(board);

    }

    @Transactional
    public List<BoardResponseDto> showAllBoards() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }

    @Transactional
    public String deleteBoard(Long id, UserDetailsImpl userDetails) {

        Board board = boardRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );

        boardRepository.delete(board);

        return "게시글 삭제 완료";
    }

}
