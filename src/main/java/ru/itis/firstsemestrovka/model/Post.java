package ru.itis.firstsemestrovka.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;
    private int price;
    private Long authorId;
    private Long photoId;
}
