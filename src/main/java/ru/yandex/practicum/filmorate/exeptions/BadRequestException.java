package ru.yandex.practicum.filmorate.exeptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}