package fr.ulco.feedbacks;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.FormService;
import fr.ulco.feedbacks.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;

@SpringBootApplication
public class FeedbacksAppApplication {
	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	public static void main(String[] args) {
		SpringApplication.run(FeedbacksAppApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//FAKE DATA FOR BDD
	@Bean
	CommandLineRunner run(UserService userService, FormService formService) {
		return args -> {
			userService.saveRole(new Role(null, USER));
			userService.saveRole(new Role(null, ADMIN));

			userService.saveUser(new User(null, "weamix", "maxime", "maxime.vitse@decathlon.com", new ArrayList<>()));
			userService.saveUser(new User(null, "alebas", "lebas", "axel.lebas@decathlon.com", new ArrayList<>()));
			userService.saveUser(new User(null, "clement", "fasquel", "clement.fasquel@eurotutu.com", new ArrayList<>()));

			userService.addRoleToUser("clement", USER);
			userService.addRoleToUser("weamix", ADMIN);
			userService.addRoleToUser("alebas", ADMIN);

			FormDto formFeedback1 = new FormDto("Feedback annuel");
			formService.addForm(formFeedback1);

			QuestionDto question11 = new QuestionDto("Comment jugez-vous mes compétences en Java ?");
			QuestionDto question12 = new QuestionDto("Est-ce que j'ai amélioré mon travail en équipe ?");
			formService.addQuestion(1L, question11);
			formService.addQuestion(1L, question12);

			FormDto formFeedback2 = new FormDto("Feedback mensuel");
			formService.addForm(formFeedback2);

			QuestionDto question21 = new QuestionDto("Est-ce que j'ai bien progressé en Java le mois dernier ?");
			formService.addQuestion(2L, question21);

			formService.addAnswer(1L, 1L, new AnswerDto("Très bien"));
		};
	}
}
