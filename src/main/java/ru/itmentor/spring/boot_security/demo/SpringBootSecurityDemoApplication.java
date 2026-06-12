package ru.itmentor.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itmentor.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.itmentor.spring.boot_security.demo.model.Roles;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.serviсe.Serviсe;


import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}
	@Bean
	CommandLineRunner initAdmin(RoleDaoImpl roleDao, Serviсe userService) {
		return args -> {
			if (userService.getAllUsers().isEmpty()) {
				Roles adminRole = new Roles("ROLE_ADMIN");
				Roles userRole = new Roles("ROLE_USER");

				roleDao.save(adminRole);
				roleDao.save(userRole);

				adminRole = roleDao.findByName("ROLE_ADMIN");
				userRole = roleDao.findByName("ROLE_USER");

				User admin = new User("admin", "admin", "Admin", "Admin", 2000);
				admin.setRoles(Set.of(adminRole, userRole));
				userService.saveUser(admin);

				User user = new User("user", "user", "User", "User", 1990);
				user.setRoles(Set.of(userRole));
				userService.saveUser(user);
			}
		};
	}

}
