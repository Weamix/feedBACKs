package fr.ulco.feedbacks.service;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Form;
import fr.ulco.feedbacks.entity.Question;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.mapper.AnswerMapper;
import fr.ulco.feedbacks.mapper.FormMapper;
import fr.ulco.feedbacks.mapper.QuestionMapper;
import fr.ulco.feedbacks.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FormDto> getAll() {
        return formRepository.findAll().stream()
                .map(formMapper::mapFormToDto)
                .collect(toList());
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
    public void addForm(FormDto form) {
        // TODO: get user from jwt token?
        User user = new User();
        formRepository.save(formMapper.mapDtoToForm(form, user));
    }

    @Override
    public void addQuestion(Long id, QuestionDto question) {
        // get form from db
        User user = new User();
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

    @Override
    public void addAnswer(Long id, Long questionId, AnswerDto answer) {
        // get form from db
        Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        // get question from form
        Question question = form.getQuestions().stream()
                .filter(q -> q.getQuestionId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        User user = new User();
        // add answer to question
        question.getAnswers().add(answerMapper.mapDtoToAnswer(answer, question, user));

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
    }
}
