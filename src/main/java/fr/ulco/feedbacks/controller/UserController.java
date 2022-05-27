package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.UpdateRoleDto;
import fr.ulco.feedbacks.dto.UserDto;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/role")
    public ResponseEntity<Void> updateRoleOnUsername(@RequestBody UpdateRoleDto updateRoleDto){
        userService.addRoleStringToUser(updateRoleDto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUserOnUserid(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserCredentialsOnUserId(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        userService.updateUserById(id, userDto);
        return ResponseEntity
                .noContent()
                .build();
    }
}
