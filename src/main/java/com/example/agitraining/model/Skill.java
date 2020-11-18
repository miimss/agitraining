package com.example.agitraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Skill {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "skillId", nullable = false, updatable = false)
	private Long skillId;
	
	@Column
	private String skillName;
	
	@Column
	private String skillDescription;
	
	@Column
	private String skillLevel;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "dogId")
	private Dog dog;
	
	public Skill() {}
	
	public Skill(String skillName, String skillDescription, String skillLevel, Dog dog) {
		this.skillName = skillName;
		this.skillDescription = skillDescription;
		this.skillLevel = skillLevel;
		this.dog = dog;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
	
}
