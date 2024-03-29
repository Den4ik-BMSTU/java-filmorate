package ru.yandex.practicum.filmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.dao.FilmDao;
import ru.yandex.practicum.filmorate.exeptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmDaoImplTest {

    private final FilmDao filmDao;
    private final LikesDaoImpl likesDao;

    @Test
    void add() {
        Film film = new Film("StarWars", "about stars and wars",
                LocalDate.of(1970, 10, 15), 120, new Mpa(1, "G"));
        Film addedFilm = filmDao.add(film);
        assertEquals(6, addedFilm.getId());
    }

    @Test
    void update() {
        Film film = new Film("StarWars", "about stars and wars",
                LocalDate.of(1970, 10, 15), 120, new Mpa(1, "G"));
        film.setId(2);
        filmDao.update(film);
        assertEquals(film, filmDao.getById(2));
    }

    @Test
    void updateWithWrongId() {
        Film film = new Film("StarWars", "about stars and wars",
                LocalDate.of(1970, 10, 15), 120, new Mpa(1, "G"));
        film.setId(18);
        NotFoundException e = assertThrows(NotFoundException.class, () -> filmDao.update(film));
        assertEquals("Фильм по ID 18 не найден!", e.getMessage());

    }

    @Test
    void delete() {
        int result = filmDao.delete(1);

        assertEquals(1, result);
    }

    @Test
    void deleteWithWrongId() {
        int result = filmDao.delete(70);

        assertEquals(0, result);
    }

    @Test
    void getById() {
        Film film = filmDao.getById(3);

        assertEquals("Звездный путь", film.getName());
    }

    @Test
    void getByIdWrongId() {
        NotFoundException e = assertThrows(NotFoundException.class, () -> filmDao.getById(66));

        assertEquals("Фильм по ID 66 не найден!", e.getMessage());
    }

    @Test
    void getAll() {
        List<Film> films = filmDao.getAll();

        assertEquals(4, films.size());
    }

    @Test
    void getMostPopular() {
        List<Film> films = filmDao.getMostPopular(1);

        assertEquals(1, films.size());
        assertEquals(8, films.get(0).getRate());
    }

    @Test
    void addLike() {
        int result = likesDao.add(3, 1);

        assertEquals(1, result);
    }

    @Test
    void addLikeWithWrongId() {
        NotFoundException e1 = assertThrows(NotFoundException.class, () -> likesDao.add(-3, 1));
        NotFoundException e2 = assertThrows(NotFoundException.class, () -> likesDao.add(3, -1));

        assertEquals("передан неверный идентификатор!", e1.getMessage());
        assertEquals("передан неверный идентификатор!", e2.getMessage());
    }

    @Test
    void removeLike() {
        likesDao.add(4, 1);
        int result = likesDao.remove(4, 1);
        assertEquals(1, result);
    }

    @Test
    void removeLikeWithWrongId() {
        NotFoundException e1 = assertThrows(NotFoundException.class, () -> likesDao.remove(-3, 1));
        NotFoundException e2 = assertThrows(NotFoundException.class, () -> likesDao.remove(3, -1));

        assertEquals("передан неверный идентификатор!", e1.getMessage());
        assertEquals("передан неверный идентификатор!", e2.getMessage());
    }
}