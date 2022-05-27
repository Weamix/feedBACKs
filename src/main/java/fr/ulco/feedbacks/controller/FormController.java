package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public Form addForm(@RequestBody FormDto formDto) {
        return formService.addForm(formDto);
    }

    @GetMapping("/form/all")
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
    public void addAnswer(@PathVariable Long id, @PathVariable Long questionId, @RequestBody AnswerDto answerDto) {
        this.formService.addAnswer(id, questionId, answerDto);
    }


    @GetMapping("/forms")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllForms() {
        return ResponseEntity
                .ok()
                .body(formService.getAllForms());
    }

    @DeleteMapping("/forms")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAllForms() {
        formService.deleteAllForms();
    }

    /*

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

    @DeleteMapping("/{id}/question/{questionId}/answer/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(@PathVariable Long id, @PathVariable Long questionId, @PathVariable Long answerId) {
        this.formService.deleteAnswer(id, questionId, answerId);
    }*/
}
