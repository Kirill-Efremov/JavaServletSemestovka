package ru.itis.firstsemestrovka.services.Implementation;

import ru.itis.firstsemestrovka.services.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public boolean matches(String password, String hashPassword) {
        return encode(password).equals(hashPassword);
    }

    @Override
    public String encode(String password) {
        long hash = 0;
        for (int i = 0; i < password.length(); i++) {
            hash = (hash * 31) + password.charAt(i);
        }
        return Long.toHexString(hash);
    }
}
