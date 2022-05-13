package fr.ulco.feedbacks.Controller;

import fr.ulco.feedbacks.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormController {
    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm() {
        return formService.getAllForm();
    }
}
