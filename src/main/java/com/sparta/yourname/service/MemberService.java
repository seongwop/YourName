package com.sparta.yourname.service;

import com.sparta.yourname.dto.CommentResponseDto;
import com.sparta.yourname.dto.MemberResponseDto;
import com.sparta.yourname.dto.MemberSummaryDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.entity.Comment;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.repository.CommentRepository;
import com.sparta.yourname.repository.UserRepository;
import com.sparta.yourname.security.UserDetailsImpl;
import com.sparta.yourname.util.CustomErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public List<MemberSummaryDto> getAllMembers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> MemberSummaryDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .specialty(user.getSpecialty())
                        .mbti(user.getMbti())
                        .build())
                .toList();
    }

    public MemberResponseDto getMemberDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(CustomErrorMessage.USER_NOT_EXIST.getMessage()));
        List<Comment> comments = commentRepository.findAllByUserId(userId);
        List<CommentResponseDto> commentResponseDto = comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return new MemberResponseDto(user, commentResponseDto);
    }


    public Comment createComment(Long userId, String content, Authentication authentication) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(CustomErrorMessage.USER_NOT_EXIST.getMessage()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String currentUser = userDetails.getUsername();

        Comment comment = new Comment(user, content, currentUser);
        return commentRepository.save(comment);
    }

//    public Comment updateComment(Long commentId, String content, Authentication authentication) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Long currentUserId = userDetails.getUser().getId();
//
//        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
//        Long commentAuthorId = comment.getUser().getId();
//
//        if (!currentUserId.equals(commentAuthorId)) {
//            throw new IllegalStateException("작성자가 아닙니다");
//        }
//
//        comment.updateContent(content);
//        return commentRepository.save(comment);
//    }

    public void deleteComment(Long commentId, Authentication authentication) {
        //여기가 현재 로그인한 사람의 정보
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String currentUserId = userDetails.getUsername();

        //
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(CustomErrorMessage.COMMENT_NOT_EXIST.getMessage()));
        String commentAuthorId = comment.getUsername();

        if (!currentUserId.equals(commentAuthorId)) {
            throw new IllegalStateException(CustomErrorMessage.WRONG_USER.getMessage());
        }

        commentRepository.delete(comment);
    }

}
