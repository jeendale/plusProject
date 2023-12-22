package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public PostResponseDto(Post savePost) {
        this.id = savePost.getId();
        this.title = savePost.getTitle();
        this.content = savePost.getContent();
        this.createdAt = savePost.getCreatedAt();
        this.modifiedAt = savePost.getModifiedAt();
    }

}
