package fr.ulco.feedbacks.repository;

import fr.ulco.feedbacks.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByUser(Long userId);

}
