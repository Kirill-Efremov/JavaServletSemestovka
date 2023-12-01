package ru.itis.firstsemestrovka.repository;

import ru.itis.firstsemestrovka.model.FavouritePost;

import java.util.List;
import java.util.Optional;

public interface FavouritePostRepository  {
    void save(Long postId , Long userId);
    void deleteByUserAndPost(Long PostId, Long userId);
    void deleteByPost(Long postId);

    List<FavouritePost> getAllFavouritePostsByUser(Long userId);
    Optional<FavouritePost> getFavouritePost(Long postId , Long userId);
    List<FavouritePost> getAllFavouritePostsByPost(Long postId);


}
