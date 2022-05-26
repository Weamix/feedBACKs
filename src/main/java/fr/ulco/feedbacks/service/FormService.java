package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FormService {

    @Qualifier("memoryData")
    private final FormRepository formRepository;

    public Form addForm(Form form) {
        return formRepository.save(form);
    }
}
