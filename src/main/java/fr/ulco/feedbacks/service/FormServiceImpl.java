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

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {


    private final FormRepository formRepository;
    private final AuthService authService;

    @Override
    public Form addForm(FormDto formDto) {
        Form form = new Form();
        form.setUserId(authService.getAuthenticatedUser().getId());
        form.setFormName(formDto.getFormName());
        form.setQuestions(formDto.getQuestions());
        form.setRecipients(formDto.getRecipients());
        return formRepository.save(form);
    }

    @Override
    public List<Form> getAllMyFormsAsAnAuthenticatedUser() {
        return formRepository.findByUserId(authService.getAuthenticatedUser().getId());
    }

    @Override
    public List<Form> getAllMyRequestsAsAnAuthenticatedUser() {
        // hide answers of other recipients, see only my answers to the requests
        User authenticatedUser = authService.getAuthenticatedUser();
        List<Form> forms = formRepository.findAll().stream().filter(form -> form.getRecipients().contains(authenticatedUser.getUsername())).collect(Collectors.toList());

        List<Form> formsWithAnswersOnlyOfAuthenticatedUser = new ArrayList<>();
        for (Form f: forms) {
            Form form = new Form();
            form.setFormId(f.getFormId());
            form.setFormName(f.getFormName());
            form.setUserId(f.getUserId());
            // we hide others recipients here
            form.setRecipients(Collections.singletonList(authenticatedUser.getUsername()));

            List<Question> questions = new ArrayList<>();
            for(Question q : f.getQuestions()){
                Question question = new Question();
                question.setQuestionId(q.getQuestionId());
                question.setContent(q.getContent());

                List<Answer> answers = new ArrayList<>();
                if(q.getAnswers() != null){
                    for (Answer a : q.getAnswers()){
                        if(a.getUserId().equals(authenticatedUser.getId())){
                            answers.add(a);
                        }
                    }
                }
                question.setAnswers(answers);
                questions.add(question);
            }
            form.setQuestions(questions);

            formsWithAnswersOnlyOfAuthenticatedUser.add(form);
        }
        return formsWithAnswersOnlyOfAuthenticatedUser;
    }

    @Override
    public Answer addAnswer(Long formId, Long questionId, AnswerDto answerDto) throws Exception {
        User user = authService.getAuthenticatedUser();
        Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        Answer answer = new Answer();

        List<String> recipients = form.getRecipients();
        if(user.getId().equals(form.getUserId())){
            throw new Exception("You can't answer to you own form");
        }
        else if(!recipients.contains(user.getUsername())) {
            throw new Exception("You are not the author of this answer on this form");
        } else {
            answer.setUserId(user.getId());
            answer.setContent(answerDto.getContent());
            answer.setAnswerId(answerDto.getAnswerId());


            Question question = form.getQuestions().stream()
                    .filter(q -> q.getQuestionId().equals(questionId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Question not found"));

            question.getAnswers().add(answer.getAnswerId(), answer);

            formRepository.save(form);
        }
        return answer;
    }

    @Override
    public void editAnswer(Long formId, Long questionId, Long answerId, AnswerDto answerDto) throws Exception {
        User user = authService.getAuthenticatedUser();
        Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));

        List<String> recipients = form.getRecipients();
        if(user.getId().equals(form.getUserId())){
            throw new Exception("You can't edit an answer on your own form");
        }
        else if(!recipients.contains(user.getUsername())) {
            throw new Exception("You are not the author of this answer on this form");
        } else {
            Question question = form.getQuestions().stream()
                    .filter(q -> q.getQuestionId().equals(questionId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("QuestionId not found"));

            Answer answer = question.getAnswers().stream()
                    .filter(a -> a.getAnswerId()==answerId)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("AnswerId not found"));

            question.getAnswers().get(answer.getAnswerId()-1).setContent(answerDto.getContent());

            formRepository.save(form);
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
    public void deleteFormById(Long formId) throws Exception {
        Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        if(!Objects.equals(form.getUserId(), authService.getAuthenticatedUser().getId())){
            throw new Exception("You don't own this form");
        } else {
            formRepository.deleteById(formId);
        }
    }

    @Override
    public Form getById(Long formId) throws Exception {
        Form form = formRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        if(!Objects.equals(form.getUserId(), authService.getAuthenticatedUser().getId())){
            throw new Exception("You don't own this form");
        } else {
            return form;
        }
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
