package com.sistema.apicr7imports.domain.response;

public class AcessResponse {
	
	private String username;
	private String token;
	
	/**
	 * @param username
	 * @param token
	 */
	public AcessResponse(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
