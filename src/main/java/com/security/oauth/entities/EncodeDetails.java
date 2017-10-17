package com.security.oauth.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENCODE_DETAILS")
public class EncodeDetails {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID", updatable = false, nullable = false)
	private Long id;
    private String salt;
    private Integer iterations;
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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

	
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    EncodeDetails() {}

    public EncodeDetails(String salt, int iterations, String password, Long Id) {
        this.salt = salt;
        this.iterations = iterations;
        this.password = password;
        this.id = Id;
    }



	
}
