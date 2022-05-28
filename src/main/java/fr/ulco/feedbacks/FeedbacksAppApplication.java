package fr.ulco.feedbacks;


import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.UserDto;
import fr.ulco.feedbacks.entity.Question;
import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.service.AuthService;
import fr.ulco.feedbacks.service.FormService;
import fr.ulco.feedbacks.service.RoleService;
import fr.ulco.feedbacks.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FeedbacksAppApplication {

	private static final String MVITSE = "mvitse";
	private static final String ALEBAS = "alebas";
	private static final String CFASQUEL = "cfasquel";

	private static final String PASSWORD_MVITSE = "maxime";
	private static final String PASSWORD_ALEBAS = "lebas";

	public static void main(String[] args) {
		SpringApplication.run(FeedbacksAppApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//FAKE DATA FOR BDD
	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService, AuthService authService, FormService formService) {
		return args -> {
			createRoles(roleService);
			createUsers(authService);
			addRolesToUsers(userService);

			formWhereMvitseAskedToAlebas(formService);
			formWhereAlebasAskedToMvitseAndCfasquel(formService);

		};
	}

	private void createRoles(RoleService roleService) {
		roleService.saveRole(new Role(null, RoleName.USER));
		roleService.saveRole(new Role(null, RoleName.ADMIN));
	}

	private void createUsers(AuthService authService) throws Exception {
		authService.saveUser(new UserDto(MVITSE, PASSWORD_MVITSE, "maxime.vitse@decathlon.com"));
		authService.saveUser(new UserDto(ALEBAS, PASSWORD_ALEBAS, "axel.lebas@decathlon.com"));
		authService.saveUser(new UserDto(CFASQUEL, "fasquel", "clement.fasquel@eurotutu.com"));
	}

	private void addRolesToUsers(UserService userService) {
		userService.addRoleToUser(CFASQUEL, RoleName.USER);
		userService.addRoleToUser(MVITSE, RoleName.ADMIN);
		userService.addRoleToUser(ALEBAS, RoleName.ADMIN);
	}

	private void formWhereMvitseAskedToAlebas(FormService formService) throws Exception {
		// Authenticated as mvitse
		authenticatedAsMvitse(MVITSE, PASSWORD_MVITSE);

		FormDto fakeForm = new FormDto();

		List<String> recipients = new ArrayList<>();
		recipients.add(ALEBAS);

		Question question = new Question();
		question.setContent("Comment jugez-vous mes compétences en Java ?");

		List<Question> questions = new ArrayList<>();
		questions.add(question);

		fakeForm.setFormName("Feedback Mai");
		fakeForm.setRecipients(recipients);
		fakeForm.setQuestions(questions);
		formService.addForm(fakeForm);

		// Authenticated as alebas
		authenticatedAsMvitse(ALEBAS, PASSWORD_ALEBAS);
		AnswerDto answerDto = new AnswerDto();
		answerDto.setContent("J'ai apprécié de travailler avec toi sur le projet Feedbacks. Tu as su faire évoluer l'API à nos besoins côté front.");
		formService.addAnswer(1L,1L, answerDto);
	}

	private void formWhereAlebasAskedToMvitseAndCfasquel(FormService formService) throws Exception {
		// Authenticated as lebas
		authenticatedAsMvitse(ALEBAS, PASSWORD_ALEBAS);

		FormDto fakeForm = new FormDto();

		List<String> recipients = new ArrayList<>();
		recipients.add(MVITSE);
		recipients.add(CFASQUEL);

		Question question1 = new Question();
		question1.setContent("Quelle appréciation me donnes-tu sur ma contribution au projet ou sur mon métier ?");

		Question question2 = new Question();
		question2.setContent("Est-ce que j'ai amélioré mon travail en équipe ?");

		List<Question> questions = new ArrayList<>();
		questions.add(question1);
		questions.add(question2);

		fakeForm.setFormName("Feedback mensuel");
		fakeForm.setRecipients(recipients);
		fakeForm.setQuestions(questions);
		formService.addForm(fakeForm);

		authenticatedAsMvitse(MVITSE, PASSWORD_MVITSE);
		AnswerDto answerDto = new AnswerDto();
		answerDto.setContent("Belle progression");
		formService.addAnswer(2L,2L, answerDto);
	}

	private void authenticatedAsMvitse(String username, String password) {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
	}
}
