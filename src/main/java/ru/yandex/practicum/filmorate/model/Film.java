package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Data
@Slf4j
public class Film {

    private int id;
    @NotNull(message = "Имя не может быть null")
    @NotBlank(message = "Имя не может быть пустым")
    private final String name;

    @Size(max = 200, message = "Описание фильма должно содержать не более 200 символов")
    @NotNull(message = "Описание фильма не может быть null")
    private final String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма должна быть положительным целочисленным числом.")
    private final int duration;

    @NotNull(message = "Рейтинг (Mpa) не может быть null")
    private final Mpa mpa;
    @NotNull(message = "Рейтинг не может быть null")
    private int rate;
    private List<Genre> genres = new ArrayList<>();
    private Set<Integer> usersLiked = new HashSet<>();

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Integer> getUsersLiked() {
        return usersLiked;
    }

    public void setUsersLiked(Set<Integer> usersLiked) {
        this.usersLiked = usersLiked;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}