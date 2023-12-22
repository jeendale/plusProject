package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.repository.PostRepository;
import com.sparta.plusproject.repository.UserRepository;
import com.sparta.plusproject.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponseDto> getPostList(UserDetailsImpl userDetails) {

        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<PostResponseDto> response = new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            response.add(new PostResponseDto(postList.get(i),userDetails));
        }
        return response;
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto, UserDetailsImpl userDetailsImpl) {
        Post post = new Post(postRequestDto, userDetailsImpl);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost,userDetailsImpl);

        return postResponseDto;
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id 게시물이 없습니다."));
        if (!Objects.equals(post.getUser().getId(), user.getUser().getId())) {
            throw new IllegalArgumentException("게시물 작성자만 수정 가능합니다");
        }
        post.update(requestDto);
    }

    public void deletePost(Long id, UserDetailsImpl user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id 게시물이 없습니다."));
        if (!Objects.equals(post.getUser().getId(), user.getUser().getId())) {
            throw new IllegalArgumentException("게시물 작성자만 삭제 가능합니다");
        }
        postRepository.delete(post);
    }
}
