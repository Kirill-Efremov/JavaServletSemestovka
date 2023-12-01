package ru.itis.firstsemestrovka.services;

import ru.itis.firstsemestrovka.dto.PostDto;

import ru.itis.firstsemestrovka.model.FavouritePost;

import java.util.List;

public interface FavouritePostService {
    List<PostDto> getAllFavouritePostsByUser(Long userId) ;
    void save(Long postId, Long userId);
    void deleteByPostId(Long postId);

    void delete(Long postId, Long userId);
    boolean checkFavouritePost(Long postId, Long userId);
    List<FavouritePost> getAllFavouritePostsByPost (Long postId);
}
