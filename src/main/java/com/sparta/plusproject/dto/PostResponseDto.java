package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.security.UserDetailsImpl;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String nickname;
    public PostResponseDto(Post savePost, UserDetailsImpl userDetails) {
        this.id = savePost.getId();
        this.title = savePost.getTitle();
        this.content = savePost.getContent();
        this.createdAt = savePost.getCreatedAt();
        this.modifiedAt = savePost.getModifiedAt();
        this.nickname=userDetails.getNickname();
    }

}
