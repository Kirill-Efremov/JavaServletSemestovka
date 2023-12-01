package ru.itis.firstsemestrovka.repository.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ru.itis.firstsemestrovka.model.FavouritePost;
import ru.itis.firstsemestrovka.repository.FavouritePostRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class FavouritePostRepositoryImpl implements FavouritePostRepository {

    private final JdbcTemplate jdbcTemplate;


    private static final String SQL_DELETE_FAVOURITE_POST_BY_USER_POST = "DELETE from like_posts where post_id = ? and author_id = ?";

    private final static String SQL_DELETE_BY_POST_ID = "DELETE from like_posts where post_id = ?";
    private final static String SQL_SELECT_ALL_POSTS_BY_AUTHOR_ID = "select * from like_posts where author_id = ?";
    private final static String SQL_SELECT_ALL_POSTS_BY_POST_ID = "select * from like_posts where post_id = ?";

    private final static String SQL_SELECT_BY_ID = "select * from like_posts where post_id = ? and author_id = ?";

    private final static String SQL_INSERT = "insert into like_posts  (post_id, author_id) VALUES ( ?, ?);";


    private final RowMapper<FavouritePost> rowMapper = (row, rowNumber) -> FavouritePost.builder().id(row.getLong("id")).postId(row.getLong("post_id")).authorId(row.getLong("author_id")).build();

    public FavouritePostRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void save(Long postId, Long userId) {
        jdbcTemplate.update(SQL_INSERT, postId, userId);
    }

    @Override
    public void deleteByUserAndPost(Long postId, Long userId) {
        jdbcTemplate.update(SQL_DELETE_FAVOURITE_POST_BY_USER_POST, postId, userId);

    }

    @Override
    public void deleteByPost(Long postId) {
        jdbcTemplate.update(SQL_DELETE_BY_POST_ID, postId);
    }


    @Override
    public List<FavouritePost> getAllFavouritePostsByUser(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS_BY_AUTHOR_ID, rowMapper, userId);
    }

    @Override
    public Optional<FavouritePost> getFavouritePost(Long postId, Long userId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, postId, userId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FavouritePost> getAllFavouritePostsByPost(Long postId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS_BY_POST_ID, rowMapper, postId);
    }


}
