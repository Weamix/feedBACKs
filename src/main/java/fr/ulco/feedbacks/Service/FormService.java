package fr.ulco.feedbacks.Service;

import fr.ulco.feedbacks.DAO.FormDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Qualifier("memoryData")
    private final FormDAO formDAO;

    @Autowired
    public FormService(FormDAO formDAO) {
        this.formDAO = formDAO;
    }

    public String getAllForm() {
        return null;
    }
}
