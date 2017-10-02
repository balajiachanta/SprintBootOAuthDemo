package com.security.oauth.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    
    private String firstname;
    private String lastname;
    
    private String email;
    private String username;
    private String password;
    
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String firstname, String lastname, String email,String username, String password,List<Role> roles) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username=username;
		this.password = password;
		this.roles = roles;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public User(){};
    
    
    
    
   
}
