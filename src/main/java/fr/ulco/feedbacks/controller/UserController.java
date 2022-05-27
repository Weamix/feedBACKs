package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    //no need to write constructors with RequiredArgsConstructor

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/usernames")
    public ResponseEntity<List<String>> getAllUsernames() {
        return ResponseEntity.ok().body(userService.getAllUsernames());
    }

}
