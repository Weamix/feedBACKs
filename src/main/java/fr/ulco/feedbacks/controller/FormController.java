package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/form")
@RestController
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Form addForm(@RequestBody FormDto formDto) throws Exception {
        return formService.addForm(formDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllMyFormsAsAnAuthenticatedUser() {
        return ResponseEntity
                .ok()
                .body(formService.getAllMyFormsAsAnAuthenticatedUser());
    }

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllMyRequestsAsAnAuthenticatedUser() {
        return ResponseEntity
                .ok()
                .body(formService.getAllMyRequestsAsAnAuthenticatedUser());
    }

    @PostMapping("/{id}/question/{questionId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Answer> addAnswer(@PathVariable Long id, @PathVariable Long questionId, @RequestBody AnswerDto answerDto) throws Exception {
        return new ResponseEntity<>(formService.addAnswer(id, questionId, answerDto),HttpStatus.CREATED);

    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllForms() {
        return ResponseEntity
                .ok()
                .body(formService.getAllForms());
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteAllForms() {
        formService.deleteAllForms();
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteFormById(@PathVariable Long id) throws Exception {
        formService.deleteFormById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Form> getFormById(@PathVariable Long id) throws Exception {
        return ResponseEntity
                .ok()
                .body(formService.getById(id));
    }

    @PutMapping("/{formId}/question/{questionId}/answer/{answerId}") @ResponseStatus(HttpStatus.OK)
    public void correctAnswer(@PathVariable Long formId, @PathVariable Long questionId, @PathVariable Long answerId, @RequestBody AnswerDto answerDto) throws Exception {
        this.formService.editAnswer(formId, questionId,answerId, answerDto);
    }

    // PUT Question

}
