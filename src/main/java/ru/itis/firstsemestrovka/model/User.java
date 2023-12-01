package ru.itis.firstsemestrovka.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private Long avatarId;
}
