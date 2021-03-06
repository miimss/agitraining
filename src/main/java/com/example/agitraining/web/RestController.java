package com.example.agitraining.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.agitraining.model.Dog;
import com.example.agitraining.model.DogRepository;
import com.example.agitraining.model.Skill;
import com.example.agitraining.model.SkillRepository;
import com.example.agitraining.model.User;
import com.example.agitraining.model.UserRepository;


@Controller
public class RestController {
	@Autowired
	private UserRepository urepo;
	@Autowired
	private DogRepository drepo;
	@Autowired
	private SkillRepository srepo;
	
	//Get list of all users
	@RequestMapping(value="/users", method = RequestMethod.GET)
    public @ResponseBody List<User> userListRest() {
		return (List<User>) urepo.findAll();
	}
	
	//Get list of all dogs
	@RequestMapping(value="/dogs", method = RequestMethod.GET)
    public @ResponseBody List<Dog> dogListRest() {
		return (List<Dog>) drepo.findAll(); 
	}
	
	//Get list of all skills
	@RequestMapping(value="/skills", method = RequestMethod.GET)
    public @ResponseBody List<Skill> skillListRest() {
		return (List<Skill>) srepo.findAll();
	}
	
	//Get selected user
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long userId) { 
    	return urepo.findById(userId);
    }
	
	//Get selected dog
	@RequestMapping(value="/dogs/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Dog> findDogRest(@PathVariable("id") Long dogId) { 
    	return drepo.findById(dogId);
    }
	
	//Get selected skill
	@RequestMapping(value="/skills/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Skill> findSkillRest(@PathVariable("id") Long skillId) { 
    	return srepo.findById(skillId);
    }
	
}
