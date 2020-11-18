package com.example.agitraining.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "userId", nullable=false, updatable=false)
	private Long userId;
	
	@Column(name="username", nullable = false, unique = true)
    private String username;
	
	@JsonIgnore
	@Column
    private String passwordHash;
	
	@Column
    private String role;
    
	@Column
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Dog> dogs;

    public User() {}
    
    public User(String username, String passwordHash, String role) {
    	super();
    	this.username = username;
    	this.passwordHash = passwordHash;
    	this.role = role;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Dog> getDogs() {
		return dogs;
	}

	public void setDogs(List<Dog> dogs) {
		this.dogs = dogs;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ ", dogs=" + dogs + "]";
	}


}
