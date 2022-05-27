package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.entity.User;
import java.util.List;

public interface UserService {

    // USER
    Boolean isUsernameFree(String email);
    User getUser(String username);
    List<User> getUsers();
    List<String> getAllUsernames();

    // ROLE
    void addRoleToUser(String username, RoleName roleName);
}