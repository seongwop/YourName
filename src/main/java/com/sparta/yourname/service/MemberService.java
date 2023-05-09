package com.sparta.yourname.service;

import com.sparta.yourname.dto.MemberResponseDto;
import com.sparta.yourname.dto.MemberSummaryDto;
import com.sparta.yourname.dto.UserResponseDto;
import com.sparta.yourname.entity.Comment;
import com.sparta.yourname.entity.User;
import com.sparta.yourname.repository.CommentRepository;
import com.sparta.yourname.repository.UserRepository;
import com.sparta.yourname.security.UserDetailsImpl;
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
                .collect(Collectors.toList());
    }

    public MemberResponseDto getMemberDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        List<Comment> comments = commentRepository.findAllByUserId(userId);

        // 여기서 필요한 데이터만 사용하여 MemberResponseDto 객체를 생성하고 반환하세요.
        // 예를 들어, 사용자 정보와 댓글 목록을 포함한 객체를 반환하려면 다음과 같이 작성할 수 있습니다.
        return new MemberResponseDto(user, comments);
    }


    public Comment createComment(Long userId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        Comment comment = new Comment(user, content);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, String content, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long currentUserId = userDetails.getUser().getId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
        Long commentAuthorId = comment.getUser().getId();

        if (!currentUserId.equals(commentAuthorId)) {
            throw new IllegalStateException("작성자가 아닙니다");
        }

        comment.updateContent(content);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long currentUserId = userDetails.getUser().getId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
        Long commentAuthorId = comment.getUser().getId();

        if (!currentUserId.equals(commentAuthorId)) {
            throw new IllegalStateException("작성자가 아닙니다");
        }

        commentRepository.delete(comment);
    }

}
