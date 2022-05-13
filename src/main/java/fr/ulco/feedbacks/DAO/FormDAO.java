package fr.ulco.feedbacks.DAO;

import fr.ulco.feedbacks.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDAO extends JpaRepository<Form, Long> {
}
