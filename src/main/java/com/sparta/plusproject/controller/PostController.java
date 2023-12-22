package com.sparta.plusproject.controller;

import com.sparta.plusproject.dto.CommonResponseDto;
import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostResponseDto> getPostList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.getPostList(userDetails);
    }
    @PostMapping
    public ResponseEntity<CommonResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        PostResponseDto postResponseDto = postService.createPost(postRequestDto,userDetailsImpl);
        return ResponseEntity.ok().body(postResponseDto);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl user){
        try {
            postService.updatePost(id,requestDto,user);
            return ResponseEntity.ok().body(new CommonResponseDto("수정완료", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponseDto>deletePost(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl user){
        try {
            postService.deletePost(id,user);
            return ResponseEntity.ok().body(new CommonResponseDto("삭제완료",HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }
}
