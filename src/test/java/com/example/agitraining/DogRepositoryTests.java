package com.example.agitraining;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.agitraining.model.Dog;
import com.example.agitraining.model.DogRepository;
import com.example.agitraining.model.User;
import com.example.agitraining.model.UserRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DogRepositoryTests {
	@Autowired
	private DogRepository drepo;
	@Autowired
	private UserRepository urepo;
	
	@Test
	public void findDogByDogId() {
		Long id = (long) 1;
		assertThat(drepo.findByDogId(id).getName()).isEqualTo("Riesa");
	}
	
	@Test
	public void createNewDog() {
		Dog dog = new Dog("Musti", "karjalankarhukoira", urepo.findByUsername("user"));
		drepo.save(dog);
		assertThat(dog.getDogId()).isNotNull();
	}
	
	@Test
	public void deleteDogById() {
		Dog dog = new Dog("Musti", "karjalankarhukoira", urepo.findByUsername("user"));
		drepo.save(dog);
		
		Long id = dog.getDogId();
		drepo.deleteById(id);
		assertThat(drepo.findById(id)).isEmpty();
		
	}
}
