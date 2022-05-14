package fr.ulco.feedbacks.repository;

import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
