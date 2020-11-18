package com.example.agitraining.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long>{
	List<Dog> findByName(String name);
	
	List<Dog> findByOwner(User owner);
	
	Dog findByDogId(Long id);
}
