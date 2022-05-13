package fr.ulco.feedbacks.Repository;

import fr.ulco.feedbacks.Entity.Form;
import fr.ulco.feedbacks.Entity.Question;
import fr.ulco.feedbacks.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

}
