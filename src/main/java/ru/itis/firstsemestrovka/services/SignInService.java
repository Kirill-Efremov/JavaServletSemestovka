package ru.itis.firstsemestrovka.services;

import ru.itis.firstsemestrovka.dto.SignInForm;
import ru.itis.firstsemestrovka.dto.UserDto;

public interface SignInService {
    UserDto signIn(SignInForm form);
}
