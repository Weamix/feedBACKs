package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.FormService;
import fr.ulco.feedbacks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

   @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FormDto>> getAllForms() {
        return ResponseEntity
                .ok()
                .body(formService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addForm(@RequestBody FormDto form, Authentication authentication) {
        this.formService.addForm(form, authentication.getName());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllFormsOfAuthenticatedUser(Authentication authentication) {
        return ResponseEntity
                .ok()
                .body(formService.getAllFormsOfAuthenticatedUser(authentication.getName()));
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

    @DeleteMapping("/{id}/question/{questionId}/answer/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(@PathVariable Long id, @PathVariable Long questionId, @PathVariable Long answerId) {
        this.formService.deleteAnswer(id, questionId, answerId);
    }
}
