package com.example.agitraining.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository  extends CrudRepository<Skill, Long>{
	List<Skill> findByDog(Optional<Dog> dog);
	
	Skill findBySkillId(Long skillId);
}
