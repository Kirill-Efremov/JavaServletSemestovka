package ru.itis.firstsemestrovka.repository;

import ru.itis.firstsemestrovka.dto.UserDto;
import ru.itis.firstsemestrovka.model.User;
import ru.itis.firstsemestrovka.repository.base.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    void updateUser(User item);

    void updateAvatarForUser(Long userId, Long fileId);
}
