package ru.itis.firstsemestrovka.exceptions;

public class FileSizeException extends RuntimeException {
    public FileSizeException(String message) {
        super(message);
    }
}
