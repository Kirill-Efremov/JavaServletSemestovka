package ru.itis.firstsemestrovka.services.Implementation;

import ru.itis.firstsemestrovka.dto.UserDto;
import ru.itis.firstsemestrovka.model.User;
import ru.itis.firstsemestrovka.repository.UsersRepository;
import ru.itis.firstsemestrovka.services.UserService;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UsersRepository userRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public void updateUser(UserDto userDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(userDto.getId()).orElseThrow(() -> new NoSuchElementException("User not found with id: " + userDto.getId())));
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepository.updateUser(user);

    }
}
