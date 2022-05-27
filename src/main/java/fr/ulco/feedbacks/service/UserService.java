package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Boolean isUsernameFree(String email);

    User getUser(String username);
    List<User> getUsers();

    void addRoleToUser(String username, RoleName roleName);

    User getAuthenticatedUser();
}