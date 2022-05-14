package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @GetMapping
    public String getForm() {
        return "Get form OK";
    }
}
