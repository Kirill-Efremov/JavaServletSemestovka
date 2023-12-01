package ru.itis.firstsemestrovka.services;

import ru.itis.firstsemestrovka.dto.SignUpForm;

public interface SignUpService {
    void signUp(SignUpForm form);

    boolean checkEmail(String email);
}
