package ru.itis.firstsemestrovka.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.firstsemestrovka.model.User;


@Data
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long avatarId;


    public static UserDto from(User user) {
        return  UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatarId(user.getAvatarId())
                .build();
    }

}
