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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/usernames")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<String>> getAllUsernames() {
        return ResponseEntity.ok().body(userService.getAllUsernames());
    }

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UpdateRoleDto> updateRoleOnUsername(@RequestBody UpdateRoleDto updateRoleDto){
        return new ResponseEntity<>(userService.addRoleStringToUser(updateRoleDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUserOnUserid(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateUserCredentialsOnUserId(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        userService.updateUserById(id, userDto);
        return ResponseEntity
                .ok()
                .build();
    }
}
