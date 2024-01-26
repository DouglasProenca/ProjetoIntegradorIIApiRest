package com.sistema.apicr7imports.domain.VO;

import java.io.Serializable;
import java.util.Date;

public class BrandVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String marca;
	private CountryVO country;
	private Date data;
	private UserVO user;

	public BrandVO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public CountryVO getCountry() {
		return country;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
}
