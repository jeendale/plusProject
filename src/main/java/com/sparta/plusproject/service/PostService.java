package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponseDto> getPostList() {

        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> response = new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            response.add(new PostResponseDto(postList.get(i)));
        }
        return response;
    }
}
