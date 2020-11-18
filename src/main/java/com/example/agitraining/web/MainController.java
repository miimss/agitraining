package com.example.agitraining.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.agitraining.model.Dog;
import com.example.agitraining.model.DogRepository;
import com.example.agitraining.model.Skill;
import com.example.agitraining.model.SkillRepository;
import com.example.agitraining.model.User;
import com.example.agitraining.model.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private DogRepository drepo;
	
	@Autowired
	private SkillRepository srepo;
	
	//Go to login-page
	@RequestMapping(value = {"/login", "/"})
	public String login() {
		return "login";
	}
	
	
	//List current users dogs to home-page
	@RequestMapping(value = "/home")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User curUser = urepo.findByUsername(username);
		model.addAttribute("user", curUser);
		model.addAttribute("dogs", drepo.findByOwner(curUser));
		
		
		return "home";
	}
	
	//Get current user, create a new dog and pass them to adddog-form
	@RequestMapping(value="/adddog")
    public String addDog(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User curUser = urepo.findByUsername(username);
		
		model.addAttribute("dog", new Dog());
		model.addAttribute("owner", curUser);
		
		return "adddog";
	}
	//Save new dog to current user
	@RequestMapping(value="/savedog", method = RequestMethod.POST)
    public String savedog(Dog dog) {
		drepo.save(dog);
		return "redirect:home";
	}
	
	//Delete selected dog
	@RequestMapping(value = "/deletedog/{id}", method = RequestMethod.GET)
	public String deleteDog(@PathVariable("id") Long dogId, Model model) {
		drepo.deleteById(dogId);
		return "redirect:../home";
	}
	
	//Get selected dogs skills and pass them to dogpage
	@RequestMapping(value="/dogpage/{id}", method = RequestMethod.GET)
	public String dogpage(@PathVariable("id") Long dogId, Model model) {
		Optional<Dog> dog = drepo.findById(dogId);
		List<Skill> skills = srepo.findByDog(dog);
		List<Skill> newSkills = new ArrayList<>();
		List<Skill> inProgressSkills = new ArrayList<>();
		List<Skill> readySkills = new ArrayList<>();
		
		for (Skill skill: skills) {
			if (skill.getSkillLevel().equals("Uusi")) {
				newSkills.add(skill);
			} else if (skill.getSkillLevel().equals("Kesken")) {
				inProgressSkills.add(skill);
			} else {
				readySkills.add(skill);
			}
		}
		model.addAttribute("dog", drepo.findByDogId(dogId));
		model.addAttribute("newSkills", newSkills);
		model.addAttribute("progressSkills", inProgressSkills);
		model.addAttribute("readySkills", readySkills);
		return "dogpage";
	}
	
	//Pass selected dog and new Skill to addskill-form
	@RequestMapping(value="/addskill/{id}")
    public String addSkill(@PathVariable("id") Long dogId, Model model) {			
		model.addAttribute("dog", drepo.findByDogId(dogId));
		model.addAttribute("skill", new Skill());
		
		return "addskill";
	}
	//Save new skill to selected dog
	@RequestMapping(value="/saveskill", method = RequestMethod.POST)
    public String saveSkill(Skill skill) {
		srepo.save(skill);
		Long dogId = skill.getDog().getDogId();
		return "redirect:/dogpage/" + dogId;
	}
	
	//Edit selected skill
	@RequestMapping(value = "/editskill/{id}", method = RequestMethod.GET)
	public String editSkill(@PathVariable("id") Long skillId, Model model) {
		Skill skill = srepo.findBySkillId(skillId);
		Dog dog = skill.getDog();
		model.addAttribute("skill", srepo.findById(skillId));
		model.addAttribute("dog", dog);
		
		return "editskill";
	}
	
	//Delete selected skill
		@RequestMapping(value = "/deleteskill/{id}", method = RequestMethod.GET)
		public String deleteSkill(@PathVariable("id") Long skillId, Model model) {
			Skill skill = srepo.findBySkillId(skillId);
			Dog dog = skill.getDog();
			Long dogId = dog.getDogId();
			
			srepo.deleteById(skillId);
			return "redirect:../dogpage/" + dogId;
		}
}
