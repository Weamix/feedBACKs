package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

/*    @Test
    void get_all_users() throws Exception {
        User axel = new User(null,"axel","laxe","axel@test", new ArrayList<>() );
        User maxime = new User(null,"maxime","amxime","maxime@test", new ArrayList<>() );
        List<User> users = new ArrayList<>();
        users.add(axel);
        users.add(maxime);

        doReturn(users).when(userService.getUsers());

        mockMvc.perform(get("/auth/users"));

    }*/
}