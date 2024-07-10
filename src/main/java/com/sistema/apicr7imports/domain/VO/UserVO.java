package com.sistema.apicr7imports.domain.VO;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String userName;

	/**
	 *
	 */
	public UserVO() {

	}

	/**
	 * @param id
	 */
	public UserVO(Long id) {
		super();
		this.id = id;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
