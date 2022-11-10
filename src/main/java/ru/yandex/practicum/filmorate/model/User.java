package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class User {

    private int id;


    @Email(message = "Не корректный адрес электронной почты")
    @NotNull(message = "Email не может быть null")
    private final String email;

    @Pattern(regexp = "^\\S*$", message = "Логин не должен содержать пробелы")
    @NotNull(message = "Логин не может быть null")
    @NotBlank(message = "Логин не может быть пустым")
    private final String login;

    private String name;

    @Past(message = "Дата рождения не может быть в будущем")
    @NotNull(message = "Дата рождения не может быть null")
    private final LocalDate birthday;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}