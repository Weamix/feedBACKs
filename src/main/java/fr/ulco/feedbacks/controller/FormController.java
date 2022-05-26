package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FormDto>> getAllForms() {
        return ResponseEntity
                .ok()
                .body(formService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addForm(@RequestBody FormDto form) {
        this.formService.addForm(form);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FormDto> getFormById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(formService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteFormById(@PathVariable Long id) {
        formService.deleteFormById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{id}/question")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion(@PathVariable Long id, @RequestBody QuestionDto question) {
        this.formService.addQuestion(id, question);
    }

    @DeleteMapping("/{id}/question/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable Long id, @PathVariable Long questionId) {
        this.formService.deleteQuestionById(id, questionId);
    }

    @PostMapping("/{id}/question/{questionId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnswer(@PathVariable Long id, @PathVariable Long questionId, @RequestBody AnswerDto answer) {
        this.formService.addAnswer(id, questionId, answer);
    }
}
