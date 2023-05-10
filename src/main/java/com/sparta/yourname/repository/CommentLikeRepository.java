package com.sparta.yourname.repository;

import com.sparta.yourname.entity.Comment;
import com.sparta.yourname.entity.CommentLike;
import com.sparta.yourname.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByCommentAndUser (Comment comment, User user);

}
