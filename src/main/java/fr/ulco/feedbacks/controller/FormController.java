package fr.ulco.feedbacks.controller;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.FormService;
import fr.ulco.feedbacks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Form addForm(@RequestBody FormDto formDto, Authentication authentication) {
        User user = userService.getUser(authentication.getName());

        Form form = new Form();
        form.setUserId(user.getId());
        form.setFormName(formDto.getFormName());
        form.setQuestions(formDto.getQuestions());
        form.setRecipients(formDto.getRecipients());
        return formService.addForm(form);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllFormsOfAuthenticatedUser(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return ResponseEntity
                .ok()
                .body(formService.getAllMyFormsAsAnAuthenticatedUser(user.getId()));
    }

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllMyRequestsAsAnAuthenticatedUser(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return ResponseEntity
                .ok()
                .body(formService.getAllMyRequestsAsAnAuthenticatedUser(user.getUsername()));
    }

    @PostMapping("/{id}/question/{questionId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnswer(@PathVariable Long id, @PathVariable Long questionId, @RequestBody AnswerDto answerDto, Authentication authentication) {
        User user = userService.getUser(authentication.getName());

        Answer answer = new Answer();
        answer.setUserId(user.getId());
        answer.setContent(answerDto.getContent());
        answer.setAnswerId(answerDto.getAnswerId());

        this.formService.addAnswer(id, questionId, answer, user.getId());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Form>> getAllForms() {
        return ResponseEntity
                .ok()
                .body(formService.getAll());
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
