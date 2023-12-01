package ru.itis.firstsemestrovka.repository.implementation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.firstsemestrovka.model.Post;
import ru.itis.firstsemestrovka.repository.PostRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_SELECT_ALL = "select * from posts;";
    private final static String SQL_SELECT_ALL_BY_USER_ID = "select * from posts where author = ?;";
    private final static String SQL_SELECT_BY_ID = "select * from posts where id = ?;";
    private final static String SQL_INSERT = "insert into posts (title, content, price, author) VALUES (?, ?, ?, ?);";
    private final static String SQL_UPDATE = "update posts set title = ?, content = ?, price = ?, author = ? where id = ?";
    private final static String SQL_DELETE_POST = "DELETE from posts where id = ?";
    private static final String SQL_SET_POST_PHOTO = "UPDATE posts SET avatar_id = ? WHERE id = ?";


    private final RowMapper<Post> rowMapper = (row, rowNumber) -> Post.builder()
            .id(row.getLong("id"))
            .title(row.getString("title"))
            .content(row.getString("content"))
            .price(row.getInt("price"))
            .authorId(row.getLong("author"))
            .photoId(row.getLong("avatar_id"))
            .build();

    public PostRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Post> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, post.getTitle());
                statement.setString(2, post.getContent());
                statement.setLong(3, post.getPrice());
                statement.setLong(4, post.getAuthorId());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                post.setId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    post.getTitle(),
                    post.getContent(),
                    post.getPrice(),
                    post.getAuthorId()
            );
        }
        return post;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_POST, id);
    }


    @Override
    public void setPhotoForPost(Long postId, Long photoId) {
        jdbcTemplate.update(
                SQL_SET_POST_PHOTO,
                photoId,
                postId
        );
    }

    @Override
    public List<Post> findAllByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_USER_ID, rowMapper, userId);
    }

}
