package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;

public interface RoleService {
    Role saveRole(Role role);
    Role findByName(RoleName roleName);
}
