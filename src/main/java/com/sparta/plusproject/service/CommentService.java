package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.CommentRequestDto;
import com.sparta.plusproject.dto.CommentResponseDto;
import com.sparta.plusproject.entity.Comment;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.repository.CommentRepository;
import com.sparta.plusproject.repository.PostRepository;
import com.sparta.plusproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long id, CommentRequestDto req, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다.")));
        if (req.getText() == null) {
            throw new IllegalArgumentException("내용을 입력하세요");
        }
        Comment comment = new Comment(post, req, userDetails);
        Comment saveComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
        return commentResponseDto;
    }
}
