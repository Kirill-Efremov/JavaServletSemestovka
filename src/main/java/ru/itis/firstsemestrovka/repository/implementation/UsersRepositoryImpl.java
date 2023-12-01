package ru.itis.firstsemestrovka.repository.implementation;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.firstsemestrovka.model.User;
import ru.itis.firstsemestrovka.repository.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String SQL_UPDATE = "update users set first_name = ?, last_name = ?, password = ?, email = ? where id = ?";

    private final static String SQL_SELECT_ALL = "select * from users;";
    private final static String SQL_INSERT = "insert into users (first_name, last_name, password, email) VALUES (?, ?, ?, ?);";
    private final static String SQL_SELECT_BY_ID = "select * from users where id = ?;";
    private final static String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    private static final String SQL_UPDATE_USER_AVATAR = "UPDATE users SET avatar_id = ? WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE from users where id = ?";

    private final RowMapper<User> rowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .avatarId(row.getLong("avatar_id"))
            .passwordHash(row.getString("password"))
            .build();


    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getPasswordHash());
                statement.setString(4, user.getEmail());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                user.setId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPasswordHash(),
                    user.getEmail(),
                    user.getId()
            );
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    public void updateUser(User item) {
        jdbcTemplate.update(
                SQL_UPDATE_USER,
                item.getFirstName(),
                item.getLastName(),
                item.getEmail(),
                item.getId()
        );
    }

    @Override
    public void updateAvatarForUser(Long userId, Long fileId) {
        jdbcTemplate.update(
                SQL_UPDATE_USER_AVATAR,
                fileId,
                userId
        );

    }
}
