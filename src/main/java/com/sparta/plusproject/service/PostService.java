package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.repository.PostRepository;
import com.sparta.plusproject.repository.UserRepository;
import com.sparta.plusproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponseDto> getPostList(UserDetailsImpl userDetails) {

        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        List<PostResponseDto> response = new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            response.add(new PostResponseDto(postList.get(i),userDetails));
        }
        return response;
    }
}
