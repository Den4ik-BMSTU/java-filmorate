package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeptions.BadRequestException;
import ru.yandex.practicum.filmorate.exeptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.validator.Validator.validate;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 1;

    @GetMapping
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film addFilm(@RequestBody @Valid Film film) throws BadRequestException {
        validate(film);
        film.setId(generateId());
        films.put(film.getId(), film);
        log.info("Фильм добавлен");
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film film) throws BadRequestException {
        validate(film);
        if (!films.containsKey(film.getId())) {
            throw new NotFoundException("Фильм по ID " + film.getId() + " не найден!");
        }
        films.put(film.getId(), film);
        log.info("Фильм обновлен");
        return film;
    }

    private int generateId() {
        return id++;
    }
}
