package ru.itis.firstsemestrovka.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SignUpForm {
    private String fistName;
    private String lastName;
    private String email;
    private String password;
  }
