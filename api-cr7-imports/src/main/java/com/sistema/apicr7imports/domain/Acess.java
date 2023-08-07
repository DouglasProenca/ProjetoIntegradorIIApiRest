package com.sistema.apicr7imports.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Acess {

	@Id
	private Long  id;
	private String user;
	private String token;

	public Acess() {

	}
	

	public Acess(Long id,String user, String acess, String token) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
	}


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
