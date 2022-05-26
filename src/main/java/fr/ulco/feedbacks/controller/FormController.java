package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @GetMapping
    public ResponseEntity<List<FormDto>> getAllForms() {
        return ResponseEntity
                .ok()
                .body(formService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDto> getFormById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(formService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addForm(@RequestBody FormDto form) {
        this.formService.addForm(form);
    }
}
