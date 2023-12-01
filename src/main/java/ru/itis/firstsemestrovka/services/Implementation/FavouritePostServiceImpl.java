package ru.itis.firstsemestrovka.services.Implementation;

import ru.itis.firstsemestrovka.dto.PostDto;

import ru.itis.firstsemestrovka.model.FavouritePost;
import ru.itis.firstsemestrovka.model.Post;
import ru.itis.firstsemestrovka.repository.FavouritePostRepository;
import ru.itis.firstsemestrovka.repository.PostRepository;
import ru.itis.firstsemestrovka.services.FavouritePostService;


import java.util.List;
import java.util.stream.Collectors;

public class FavouritePostServiceImpl implements FavouritePostService {
    private final FavouritePostRepository favouritePostRepository;
    private final PostRepository postRepository;

    public FavouritePostServiceImpl(FavouritePostRepository favouritePostRepository, PostRepository postRepository) {
        this.favouritePostRepository = favouritePostRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getAllFavouritePostsByUser(Long userId) {
        List<Long> postIds = favouritePostRepository.getAllFavouritePostsByUser(userId)
                .stream()
                .map(FavouritePost::getPostId)
                .toList();

        List<Post> listAllPosts = postRepository.findAll().stream()
                .filter(post -> postIds.contains(post.getId()))
                .toList();
        return listAllPosts.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Long postId, Long userId) {
        favouritePostRepository.save(postId, userId);
    }

    @Override
    public void deleteByPostId(Long postId) {
        favouritePostRepository.deleteByPost(postId);
    }

    @Override
    public void delete(Long postId, Long userId) {
        favouritePostRepository.deleteByUserAndPost(postId, userId);
    }

    @Override
    public boolean checkFavouritePost(Long postId, Long userId) {
        return favouritePostRepository.getFavouritePost(postId, userId).isEmpty();
    }

    @Override
    public List<FavouritePost> getAllFavouritePostsByPost(Long postId) {
        return favouritePostRepository.getAllFavouritePostsByPost(postId);
    }
}
