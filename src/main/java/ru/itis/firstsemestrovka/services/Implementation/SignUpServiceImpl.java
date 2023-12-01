package ru.itis.firstsemestrovka.services.Implementation;

import ru.itis.firstsemestrovka.dto.SignUpForm;
import ru.itis.firstsemestrovka.exceptions.AuthorizationException;
import ru.itis.firstsemestrovka.model.User;
import ru.itis.firstsemestrovka.repository.UsersRepository;
import ru.itis.firstsemestrovka.services.PasswordEncoder;
import ru.itis.firstsemestrovka.services.SignUpService;

import java.util.Optional;


public class SignUpServiceImpl implements SignUpService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(SignUpForm form) {
        if (form.getFistName() == null) {
            throw new AuthorizationException("Fist name  cannot be null");
        }
        if (form.getLastName() == null) {
            throw new AuthorizationException("Last name cannot be null");
        }
        if (form.getEmail() == null) {
            throw new AuthorizationException("Email cannot be null");
        }
        Optional<User> optionalUser = usersRepository.findByEmail(form.getEmail());
        if (optionalUser.isPresent()) {
            throw new AuthorizationException("User with email " + form.getEmail() + " already exists.");
        }
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        User user = User.builder()
                .email(form.getEmail())
                .firstName(form.getFistName())
                .lastName(form.getLastName())
                .passwordHash(form.getPassword())
                .build();
        usersRepository.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return usersRepository.findByEmail(email).isEmpty();
    }
}
