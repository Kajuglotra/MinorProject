package org.gfg.minor1;

import org.gfg.minor1.Repository.ExpenseRepository;
import org.gfg.minor1.Repository.UserRepository;
import org.gfg.minor1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Minor1Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Minor1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user =userRepository.findByMail("john@gmail.com");
		System.out.println(user);
	}
}
