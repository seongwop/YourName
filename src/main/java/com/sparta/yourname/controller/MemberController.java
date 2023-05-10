package com.sparta.yourname.controller;

import com.sparta.yourname.dto.CommentRequestDto;
import com.sparta.yourname.dto.CommentResponseDto;
import com.sparta.yourname.dto.MemberResponseDto;
import com.sparta.yourname.dto.MemberSummaryDto;
import com.sparta.yourname.entity.Comment;
import com.sparta.yourname.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping
    public ResponseEntity<List<MemberSummaryDto>> getAllMembers() {
        List<MemberSummaryDto> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberResponseDto> getMemberDetails(@PathVariable Long userId) {
        MemberResponseDto memberDetails = memberService.getMemberDetails(userId);
        return ResponseEntity.ok(memberDetails);
    }

    @PostMapping("/{userId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long userId, @RequestBody CommentRequestDto commentRequestDto, Authentication authentication) {
        Comment comment = memberService.createComment(userId, commentRequestDto.getContent(), authentication);
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

//    @PutMapping("/{userId}/comments/{commentId}")
//    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody String content, Authentication authentication) {
//
//        Comment comment = memberService.updateComment(commentId, content, authentication);
//        return ResponseEntity.ok(comment);
//    }

    @DeleteMapping("/{userId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        memberService.deleteComment(commentId,authentication);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/comments/{commentId}/like")
    public ResponseEntity<CommentResponseDto> likeComment(@PathVariable Long commentId, Authentication authentication) {
        memberService.likeComment(commentId,authentication);
        return ResponseEntity.noContent().build();
    }

}
