package fr.ulco.feedbacks;


import fr.ulco.feedbacks.dto.UserDto;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	CommandLineRunner run(UserService userService, RoleService roleService, AuthService authService, FormService formService) {
		return args -> {
			roleService.saveRole(new Role(null, RoleName.USER));
			roleService.saveRole(new Role(null, RoleName.ADMIN));

			authService.saveUser(new UserDto("mvitse", "maxime", "maxime.vitse@decathlon.com"));
			authService.saveUser(new UserDto("alebas", "lebas", "axel.lebas@decathlon.com"));
			authService.saveUser(new UserDto("cfasquel", "fasquel", "clement.fasquel@eurotutu.com"));

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
