package fr.ulco.feedbacks.Service;

import fr.ulco.feedbacks.Repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Qualifier("memoryData")
    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }
}
