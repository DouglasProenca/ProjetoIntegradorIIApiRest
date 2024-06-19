package com.sistema.apicr7imports.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "rc_pais")
public class Country extends RepresentationModel<Country> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "paisid")
	private Long idCountry;
	@ApiModelProperty(value = "Nome do Pais em Português.")
	@Column(name = "paisnome")
	private String namePort;
	@Column(name = "paisname")
	private String nameEng;

	public Country() {

	}

	public Country(Long idCountry, String namePort, String nameEng) {
		super();
		this.idCountry = idCountry;
		this.namePort = namePort;
		this.nameEng = nameEng;
	}

	public Country(int id) {
		this.idCountry = Long.valueOf(id);
	}

	public Long getId() {
		return idCountry;
	}

	public String getNamePort() {
		return namePort;
	}

	public String getNameEng() {
		return nameEng;
	}

	/**
	 * @param idCountry the idCountry to set
	 */
	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}
	
	
}
