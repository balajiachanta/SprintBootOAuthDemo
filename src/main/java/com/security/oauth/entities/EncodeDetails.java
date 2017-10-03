package com.security.oauth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EncodeDetails {

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String userName;
    
    private Integer iterations;
    public Integer getIterations() {
		return iterations;
	}

	public void setIterations(Integer iterations) {
		this.iterations = iterations;
	}

	public Integer getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(Integer keyLength) {
		this.keyLength = keyLength;
	}

	private Integer keyLength;

    EncodeDetails() {}

    public EncodeDetails(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
