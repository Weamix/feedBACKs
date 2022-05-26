package fr.ulco.feedbacks;

import fr.ulco.feedbacks.dto.AnswerDto;
import fr.ulco.feedbacks.dto.FormDto;
import fr.ulco.feedbacks.dto.QuestionDto;
import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.FormService;
import fr.ulco.feedbacks.service.RoleService;
import fr.ulco.feedbacks.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class FeedbacksAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbacksAppApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//FAKE DATA FOR BDD
	@Bean
	CommandLineRunner run(UserService userService, FormService formService, RoleService roleService) {
		return args -> {
			roleService.saveRole(new Role(null, RoleName.USER));
			roleService.saveRole(new Role(null, RoleName.ADMIN));

			userService.saveUser(new User(null, "mvitse", "maxime", "maxime.vitse@decathlon.com", new HashSet<>()));
			userService.saveUser(new User(null, "alebas", "lebas", "axel.lebas@decathlon.com", new HashSet<>()));
			userService.saveUser(new User(null, "cfasquel", "fasquel", "clement.fasquel@eurotutu.com", new HashSet<>()));

			userService.addRoleToUser("cfasquel", RoleName.USER);
			userService.addRoleToUser("mvitse", RoleName.ADMIN);
			userService.addRoleToUser("alebas", RoleName.ADMIN);

			/*FormDto formFeedback1 = new FormDto("Feedback annuel");
			formService.addForm(formFeedback1, "alebas");

			QuestionDto question11 = new QuestionDto("Comment jugez-vous mes compétences en Java ?");
			QuestionDto question12 = new QuestionDto("Est-ce que j'ai amélioré mon travail en équipe ?");
			formService.addQuestion(1L, question11);
			formService.addQuestion(1L, question12);

			FormDto formFeedback2 = new FormDto("Feedback mensuel");
			formService.addForm(formFeedback2, "weamix");

			QuestionDto question21 = new QuestionDto("Est-ce que j'ai bien progressé en Java le mois dernier ?");
			formService.addQuestion(2L, question21);

			formService.addAnswer(1L, 1L, new AnswerDto("Très bien"));*/
		};
	}
}
