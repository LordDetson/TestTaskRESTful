package by.babanin.testtask;

import by.babanin.testtask.dao.UserRepository;
import by.babanin.testtask.entity.User;
import by.babanin.testtask.entity.UserRole;
import by.babanin.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TestTaskApplication implements CommandLineRunner {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public TestTaskApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String username = "admin";
		String password = "a";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Set<UserRole> roles = new HashSet<>();
		roles.add(UserRole.USER);
		roles.add(UserRole.ADMINISTRATOR);
		user.setRoles(roles);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.deleteAll();
		userRepository.save(user);

		User admin = userRepository.findByUsername(user.getUsername());
		System.out.println("----- Administrator profile ------");
		System.out.println(admin);
		System.out.println("Password: " + password);
	}
}
