package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.SignUpDto;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    //no need to write constructors with RequiredArgsConstructor
    private final AuthService authService;

    // Login avec Spring Security : géré dans les Filter + SecurityConfig

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignUpDto signUpDto) throws Exception {
        return ResponseEntity.ok().body(authService.saveUser(signUpDto));
    }

    @GetMapping("/refresh")
    public void refreshJwtToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshJWTofUser(request, response);
    }
}
