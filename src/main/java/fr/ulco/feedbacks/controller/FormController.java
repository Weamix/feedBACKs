package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @GetMapping
    public String getForm() {
        return "Get form OK";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addForm(@RequestBody Form form) {
        this.formService.addForm(form);
    }
}
