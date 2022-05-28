package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.UpdateRoleDto;
import fr.ulco.feedbacks.dto.UserDto;
import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    // constructors are initialized with lombook RequiredArgsConstructor
    private final UserRepository userRepository;
    private final RoleService roleService;

    // USER

    @Override
    public Boolean isUsernameFree(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        for (User u: getUsers()) {
            usernames.add(u.getUsername());
        }
        return usernames;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserById(Long id, UserDto userDto) throws Exception {
        userRepository.findById(id).map(user -> {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            if(userDto.getPassword() == null){
                user.setPassword(user.getPassword());
            } else{
                user.setPassword(userDto.getPassword());
            }
            return userRepository.save(user);
        })
                .orElseThrow(() -> new Exception("UserId not found"));
    }

    // important for Spring security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName().toString()))
            );
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    // ROLE

    @Override
    public void addRoleToUser(String username, RoleName roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleService.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void addRoleStringToUser(UpdateRoleDto updateRoleDto) {
        User user = userRepository.findByUsername(updateRoleDto.getUsername());
        RoleName roleName;
        if(updateRoleDto.getRole().equals("ADMIN")){
            roleName = RoleName.ADMIN;
        } else{
            roleName = RoleName.USER;
        }
        Role r = roleService.findByName(roleName);
        user.getRoles().add(r);
    }
}