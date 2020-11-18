package com.example.agitraining.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dogId", nullable = false, updatable = false)
	private Long dogId;

	@Column
	private String name;

	@Column
	private String breed;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private User owner;

	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
	private List<Skill> skills;
	
	public Dog() {
	}
	
	public Dog(String name, String breed) {
		super();
		this.name = null;
		this.breed = null;
	}

	public Dog(String name, String breed, User owner) {
		super();
		this.name = name;
		this.breed = breed;
		this.owner = owner;
	}

	public Long getDogId() {
		return dogId;
	}

	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
