package fr.ulco.feedbacks;

import fr.ulco.feedbacks.entity.Role;
import fr.ulco.feedbacks.entity.User;
import fr.ulco.feedbacks.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class FeedbacksAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbacksAppApplication.class, args);
	}

	//FAKE DATA FOR BDD
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "USER"));
			userService.saveRole(new Role(null, "ADMIN"));

			userService.saveUser(new User(null, "weamix", "maxime", "maxime.vitse@decathlon.com", new ArrayList<>()));
			userService.saveUser(new User(null, "alebas", "lebas", "axel.lebas@decathlon.com", new ArrayList<>()));
			userService.saveUser(new User(null, "clement", "fasquel", "clement.fasquel@eurotutu.com", new ArrayList<>()));

			userService.addRoleToUser("clement", "USER");
			userService.addRoleToUser("weamix", "ADMIN");
			userService.addRoleToUser("alebas", "ADMIN");
		};
	}
}
