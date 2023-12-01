package ru.itis.firstsemestrovka.repository;

import ru.itis.firstsemestrovka.model.Post;
import ru.itis.firstsemestrovka.repository.base.CrudRepository;
import java.util.List;


public interface PostRepository extends CrudRepository<Post, Long> {

    void setPhotoForPost(Long postId, Long photoId);

    List<Post> findAllByUserId(Long userId);


}
