package ru.itis.firstsemestrovka.dto;

import lombok.*;
import ru.itis.firstsemestrovka.model.Post;

@Data
@Builder

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private Long authorId;
    private Long photoId;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .price(post.getPrice())
                .content(post.getContent())
                .authorId(post.getAuthorId())
                .photoId(post.getPhotoId())
                .build();
    }
}
