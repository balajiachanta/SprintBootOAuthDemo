package com.security.oauth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENCODE_DETAILS")
public class EncodeDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String salt;
	private String username;
    private Integer iterations;
	private String password;
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIterations() {
		return iterations;
	}

	public void setIterations(Integer iterations) {
		this.iterations = iterations;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    EncodeDetails() {}

    public EncodeDetails(String salt, String username, int iterations, String password) {
        this.salt = salt;
        this.username = username;
        this.iterations = iterations;
        this.password = password;
    }



	
}
