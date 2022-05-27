package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.Question;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final UserService userService;

    @Override
    public Form addForm(FormDto formDto) {
        Form form = new Form();
        form.setUserId(userService.getAuthenticatedUser().getId());
        form.setFormName(formDto.getFormName());
        form.setQuestions(formDto.getQuestions());
        form.setRecipients(formDto.getRecipients());
        return formRepository.save(form);
    }

    @Override
    public List<Form> getAllMyFormsAsAnAuthenticatedUser() {
        return formRepository.findByUserId(userService.getAuthenticatedUser().getId());
    }

    @Override
    public List<Form> getAllMyRequestsAsAnAuthenticatedUser() {
        return formRepository.findAll().stream().filter(form -> form.getRecipients().contains(userService.getAuthenticatedUser().getUsername())).collect(Collectors.toList());
    }

    @Override
    public void addAnswer(Long formId, Long questionId, AnswerDto answerDto) throws Exception {
        User user = userService.getAuthenticatedUser();
        Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));

        List<String> recipients = form.getRecipients();
        if(recipients.contains(user.getUsername()) && !user.getId().equals(form.getUserId())){
            Answer answer = new Answer();
            answer.setUserId(user.getId());
            answer.setContent(answerDto.getContent());
            answer.setAnswerId(answerDto.getAnswerId());


            Question question = form.getQuestions().stream()
                    .filter(q -> q.getQuestionId().equals(questionId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Question not found"));

            question.getAnswers().add(answer.getAnswerId(), answer);

            formRepository.save(form);
        } else {
            throw new Exception("You are not invited to answer this form");
        }
    }

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public void deleteAllForms() {
        formRepository.deleteAll();
    }

    @Override
    public void deleteFormById(Long id) {
        formRepository.deleteById(id);
    }

    @Override
    public Form getById(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found"));
    }

    /*
    @Override
    public void addQuestion(Long id, QuestionDto question) {
        // static username for now
        // TODO: get user from jwt token?
        User user = userRepository.findByUsername("weamix");
        Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        // add question to form
        form.getQuestions().add(questionMapper.mapDtoToQuestion(question, user, form));

        // update form updatedAt attribute
        form.setUpdatedAt(Instant.now());
    }

    @Override
    public void deleteQuestionById(Long id, Long questionId) {
        // get form from db
        Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        // remove question from form
        form.getQuestions().removeIf(question -> question.getQuestionId().equals(questionId));

        // update form updatedAt attribute
        form.setUpdatedAt(Instant.now());
    }

    public void deleteAnswer(Long id, Long questionId, Long answerId) {
        // get form from db
        Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        // get question from form
        Question question = form.getQuestions().stream()
                .filter(q -> q.getQuestionId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        // remove answer from question
        question.getAnswers().removeIf(answer -> answer.getAnswerId().equals(answerId));

        // update form updatedAt attribute
        form.setUpdatedAt(Instant.now());
    }*/
}
