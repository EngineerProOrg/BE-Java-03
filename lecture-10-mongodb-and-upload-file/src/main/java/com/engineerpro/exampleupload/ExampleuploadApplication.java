package com.engineerpro.exampleupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.engineerpro.exampleupload.model.User;
import com.engineerpro.exampleupload.repository.UserRepository;

@SpringBootApplication
public class ExampleuploadApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ExampleuploadApplication.class, args);
		// UserRepository userRepository = context.getBean(UserRepository.class);
		// userRepository.save(User.builder()
		// .firstName("Andy")
		// .lastName("Dieu")
		// .profileImgUrl(
		// null)
		// .build());

		// userRepository.save(User.builder()
		// .firstName("Kai")
		// .lastName("Dieu")
		// .profileImgUrl(
		// null)
		// .build());
	}

}
