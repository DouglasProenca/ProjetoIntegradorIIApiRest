package com.sistema.apicr7imports.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sistema.apicr7imports.domain.Dto.UserDTO;

@Entity
@Table(name = "rc_marca")
public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "marca")
	private String marca;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais")
	private Country country;
	
	@Column(name = "[date]")
	private Date data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	private UserDTO user;
	
	public Brand() {
		
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
