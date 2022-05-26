package fr.ulco.feedbacks;

import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.RoleName;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.RoleService;
import fr.ulco.feedbacks.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

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
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
			roleService.saveRole(new Role(null, RoleName.USER));
			roleService.saveRole(new Role(null, RoleName.ADMIN));

			userService.saveUser(new User(null, "weamix", "maxime", "maxime.vitse@decathlon.com", new HashSet<>()));
			userService.saveUser(new User(null, "alebas", "lebas", "axel.lebas@decathlon.com", new HashSet<>()));
			userService.saveUser(new User(null, "clement", "fasquel", "clement.fasquel@eurotutu.com", new HashSet<>()));

			userService.addRoleToUser("clement", RoleName.USER);
			userService.addRoleToUser("weamix", RoleName.ADMIN);
			userService.addRoleToUser("alebas", RoleName.ADMIN);
		};
	}
}
