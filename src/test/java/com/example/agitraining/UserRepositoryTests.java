package com.example.agitraining;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.agitraining.model.User;
import com.example.agitraining.model.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository urepo;
	
	@Test
	public void findUserByUsername() {
		User user = urepo.findByUsername("user");
		assertThat(user.getUsername()).isEqualTo("user");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("pekka", "$2y$12$tqUpUJNy7bA/thNlipiKWe1N0FRptMT0yE9mW0DeOMN27eKOU8vAS", "USER");
		urepo.save(user);
		assertThat(user.getUserId()).isNotNull();
	}

}
