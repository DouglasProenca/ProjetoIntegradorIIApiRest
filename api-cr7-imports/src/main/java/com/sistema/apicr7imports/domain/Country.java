package com.sistema.apicr7imports.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rc_pais")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "paisId")
	private Long id;
	@Column(name = "paisNome")
	private String namePort;
	@Column(name = "paisName")
	private String nameEng;

	public Country() {

	}

	public Country(Long id, String namePort, String nameEng) {
		super();
		this.id = id;
		this.namePort = namePort;
		this.nameEng = nameEng;
	}

	public Long getId() {
		return id;
	}

	public String getNamePort() {
		return namePort;
	}

	public String getNameEng() {
		return nameEng;
	}	
}
