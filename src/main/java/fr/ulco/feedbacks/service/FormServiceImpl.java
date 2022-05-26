package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.entity.Answer;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.Question;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.repository.FormRepository;
import fr.ulco.feedbacks.repository.UserRepository;
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

    @Override
    public Form addForm(Form form) {
        return formRepository.save(form);
    }

    @Override
    public List<Form> getAllMyFormsAsAnAuthenticatedUser(Long userId) {
        return formRepository.findByUserId(userId);
    }

    @Override
    public List<Form> getAllMyRequestsAsAnAuthenticatedUser(String username) {
        return formRepository.findAll().stream().filter(form -> form.getRecipients().contains(username)).collect(Collectors.toList());
    }

    @Override
    public void addAnswer(Long id, Long questionId, Answer answer, Long userId) {
        // get form from db
        Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        //formRepository.findAll().stream().filter(f -> f.getRecipients().contains(username)).findAny().orElseThrow(() -> new IllegalArgumentException("You are not invited to answer to this form"));
        // get question from form
        Question question = form.getQuestions().stream()
                .filter(q -> q.getQuestionId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        // add answer to question
        question.getAnswers().add(answer.getAnswerId(), answer);

        formRepository.save(form);
    }

    @Override
    public List<Form> getAll() {
        return formRepository.findAll();
    }

    /*
    @Override
    public void addForm(FormDto form, String username) {
        User user = userRepository.findByUsername(username);
        formRepository.save(formMapper.mapDtoToForm(form, user));
    }

    @Transactional(readOnly = true)
    @Override
    public List<FormDto> getAll() {
        return formRepository.findAll().stream()
                .map(formMapper::mapFormToDto)
                .collect(toList());
    }

    @Override
    public List<Form> getAllFormsOfAuthenticatedUser(String username) {
        User user = userRepository.findByUsername(username);
        return formRepository.findByUser(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public FormDto getById(Long id) {
        return formMapper.mapFormToDto(formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found")));
    }

    @Override
    public void deleteFormById(Long id) {
        formRepository.deleteById(id);
    }

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
