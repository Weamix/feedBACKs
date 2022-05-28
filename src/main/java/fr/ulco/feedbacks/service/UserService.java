package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.UpdateRoleDto;
import fr.ulco.feedbacks.dto.UserDto;
import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.entity.User;

import java.util.List;

public interface UserService {

    // USER
    Boolean isUsernameFree(String email);
    User getUser(String username);
    List<User> getUsers();
    List<String> getAllUsernamesExceptAuthenticatedUser();
    void deleteUserById(Long id);
    void updateUserById(Long id, UserDto userDto) throws Exception;

    // ROLE
    void addRoleToUser(String username, RoleName roleName);
    UpdateRoleDto addRoleStringToUser(UpdateRoleDto updateRoleDto);
}