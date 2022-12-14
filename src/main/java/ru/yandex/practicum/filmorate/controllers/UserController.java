package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping
    public List<User> getUsers() {
        log.info("Получен GET запрос /users.");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        log.info("Получен GET запрос /users/{}.", id);
        return userService.getById(id);
    }

    @PostMapping
    public User addUser(@RequestBody @Valid User user) {
        log.info("Получен POST запрос /users. Передано: {}", user);
        return userService.add(user);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        log.info("Получен PUT запрос /users. Передано: {}", user);
        return userService.update(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addToFriends(@PathVariable int id, @PathVariable int friendId) {
        log.info("Получен PUT запрос /users/{}/friends/{}.", id, friendId);
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFromFriends(@PathVariable int id, @PathVariable int friendId) {
        log.info("Получен DELETE запрос /users/{}/friends/{}.", id, friendId);
        userService.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable int id) {
        log.info("Получен GET запрос /users/{}/friends.", id);
        return userService.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getMutualFriends(@PathVariable int id, @PathVariable int otherId) {
        log.info("Получен GET запрос /users/{}/friends/common/{}", id, otherId);
        return userService.getMutualFriends(id, otherId);
    }
}