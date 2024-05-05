package com.sistema.apicr7imports.domain.VO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class BrandVO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	private String marca;
	private CountryVO country;
	private Date data;
	private UserVO user;

	/**
	 * 
	 */
	public BrandVO() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the country
	 */
	public CountryVO getCountry() {
		return country;
	}

	/**
	 * @param country
	 */
	public void setCountry(CountryVO country) {
		this.country = country;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the user
	 */
	public UserVO getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(UserVO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return id + ";" + marca + ";" + country.getNamePort() + ";" + new SimpleDateFormat("dd/MM/yyyy").format(data) + ";" + user.getUserName();
	}
}
