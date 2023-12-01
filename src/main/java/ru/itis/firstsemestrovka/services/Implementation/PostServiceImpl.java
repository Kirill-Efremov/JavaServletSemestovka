package ru.itis.firstsemestrovka.services.Implementation;

import ru.itis.firstsemestrovka.dto.PostDto;
import ru.itis.firstsemestrovka.model.Post;
import ru.itis.firstsemestrovka.repository.FavouritePostRepository;
import ru.itis.firstsemestrovka.repository.PostRepository;
import ru.itis.firstsemestrovka.services.PostService;

import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository,FavouritePostRepository favouritePostRepository) {
        this.postRepository = postRepository;

    }

    @Override
    public PostDto addPost(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .price(postDto.getPrice())
                .authorId(postDto.getAuthorId())
                .photoId(postDto.getPhotoId())
                .build();
        Post savedpost = postRepository.save(post);
        return PostDto.from(savedpost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }
    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        return postRepository.findAllByUserId(userId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long postId) {
        postRepository.delete(postId);
    }
}
