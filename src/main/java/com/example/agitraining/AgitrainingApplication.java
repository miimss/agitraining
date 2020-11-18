package com.example.agitraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.agitraining.model.Dog;
import com.example.agitraining.model.DogRepository;
import com.example.agitraining.model.Skill;
import com.example.agitraining.model.SkillRepository;
import com.example.agitraining.model.User;
import com.example.agitraining.model.UserRepository;

@SpringBootApplication
public class AgitrainingApplication {
	private static final Logger log = LoggerFactory.getLogger(AgitrainingApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AgitrainingApplication.class, args);
	}

	@Bean
	public CommandLineRunner agilityDemo(DogRepository drepo, SkillRepository srepo, UserRepository urepo) {
		return (args) -> {
			log.info("Save a few users");
			urepo.save(new User("user", "$2y$12$tqUpUJNy7bA/thNlipiKWe1N0FRptMT0yE9mW0DeOMN27eKOU8vAS", "USER"));
			urepo.save(new User("admin", "$2y$12$SFgoUMobWWadz4ifbGoEEu/Pz8pIwjhOsp8o8YDH9/KGvfo6vOCxS", "ADMIN"));
			
			log.info("Save a few dogs");
			drepo.save(new Dog("Riesa", "borderterrieri", urepo.findByUsername("user")));
			drepo.save(new Dog("Turpo", "borderterrieri", urepo.findByUsername("user")));
			drepo.save(new Dog("Stella", "australianpaimenkoira", urepo.findByUsername("admin")));
			
			log.info("Save a few skills");
			srepo.save(new Skill("Avokulmat", "Keppien sisäänmenon kaikista avokulmista", "Valmis", drepo.findByName("Riesa").get(0)));
			srepo.save(new Skill("Umpikulmat", "Keppien sisäänmenon kaikista umpikulmista", "Valmis", drepo.findByName("Riesa").get(0)));
			srepo.save(new Skill("Lähtöjättö", "Pysyy lähdössä, jos ei ole häiriöitä", "Kesken", drepo.findByName("Turpo").get(0)));
			srepo.save(new Skill("Juoksukontaktit", "Ei vielä aloitettu", "Uusi", drepo.findByName("Stella").get(0)));
		};
	}
}
