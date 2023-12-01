package ru.itis.firstsemestrovka.services;

import ru.itis.firstsemestrovka.dto.PostDto;


import java.util.List;

public interface PostService {
    PostDto addPost(PostDto postDto);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByUserId(Long userId);

    void delete(Long postId);
}
