package fr.ulco.feedbacks.repository;

import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}