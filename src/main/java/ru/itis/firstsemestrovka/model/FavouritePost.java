package ru.itis.firstsemestrovka.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavouritePost {
    private Long id;
    private Long postId;
    private Long authorId;

}
