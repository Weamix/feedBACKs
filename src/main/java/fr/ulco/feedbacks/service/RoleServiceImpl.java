package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

}
