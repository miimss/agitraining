package com.example.agitraining;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.agitraining.model.Dog;
import com.example.agitraining.model.DogRepository;
import com.example.agitraining.model.Skill;
import com.example.agitraining.model.SkillRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SkillRepositoryTests {
	@Autowired
	private SkillRepository srepo;
	
	@Autowired
	private DogRepository drepo;

	
	@Test
	public void findSkillBySkillId() {
		Long id = (long) 1;
		assertThat(srepo.findBySkillId(id).getSkillName()).isEqualTo("Avokulmat");
	}
	
	@Test
	public void createNewSkill() {
		Long id = (long) 1;
		Skill skill = new Skill("Sylikäännökset", "Tulee tarpeeksi lähelle ja lähtee oikeaan suuntaan", "Valmis", drepo.findByDogId(id));
		srepo.save(skill);
		assertThat(skill.getSkillId()).isNotNull();
	}
	
	@Test
	public void deleteDogById() {
		Long dogId = (long) 1;
		Skill skill = new Skill("Sylikäännökset", "Tulee tarpeeksi lähelle ja lähtee oikeaan suuntaan", "Valmis", drepo.findByDogId(dogId));
		srepo.save(skill);
		
		Long id = skill.getSkillId();
		srepo.deleteById(id);
		assertThat(srepo.findById(id)).isEmpty();
		
	}
}
