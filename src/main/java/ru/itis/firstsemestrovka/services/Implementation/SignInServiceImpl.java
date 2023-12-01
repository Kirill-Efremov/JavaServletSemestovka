package ru.itis.firstsemestrovka.services.Implementation;

import ru.itis.firstsemestrovka.dto.SignInForm;
import ru.itis.firstsemestrovka.dto.UserDto;
import ru.itis.firstsemestrovka.exceptions.AuthorizationException;
import ru.itis.firstsemestrovka.model.User;
import ru.itis.firstsemestrovka.repository.UsersRepository;
import ru.itis.firstsemestrovka.services.PasswordEncoder;
import ru.itis.firstsemestrovka.services.SignInService;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public SignInServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(SignInForm form) {
        if (form.getEmail() == null) {
            throw new AuthorizationException("Email cannot be null");
        }
        Optional<User> optionalUser = usersRepository.findByEmail(form.getEmail());
        if (optionalUser.isEmpty()) {
            throw new AuthorizationException("User with email " + form.getEmail() + " not found.");
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(form.getPassword(), user.getPasswordHash())) {
            throw new AuthorizationException("Wrong password");
        }
        return UserDto.from(user);
    }
}
