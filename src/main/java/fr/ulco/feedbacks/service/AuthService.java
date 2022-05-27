package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.UserDto;
import fr.ulco.feedbacks.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {
    User saveUser(UserDto userDto) throws Exception;
    void refreshJWTofUser(HttpServletRequest request, HttpServletResponse response) throws IOException;
    User getAuthenticatedUser();
}
