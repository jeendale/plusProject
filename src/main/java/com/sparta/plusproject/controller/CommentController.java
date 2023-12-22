package com.sparta.plusproject.controller;

import com.sparta.plusproject.dto.CommentRequestDto;
import com.sparta.plusproject.dto.CommentResponseDto;
import com.sparta.plusproject.dto.CommonResponseDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{id}")
    public ResponseEntity<CommonResponseDto> postComment(@PathVariable Long id, @RequestBody CommentRequestDto req, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            CommentResponseDto commentRes = commentService.createComment(id,req,userDetails);
            return ResponseEntity.ok().body(commentRes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

}
