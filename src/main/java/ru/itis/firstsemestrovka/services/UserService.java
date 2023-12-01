package ru.itis.firstsemestrovka.services;

import ru.itis.firstsemestrovka.dto.UserDto;
import ru.itis.firstsemestrovka.model.User;

import java.io.IOException;


public interface UserService {
    User findById(Long id);

    void updateUser(UserDto user) throws IOException;
}
