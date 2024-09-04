package com.sistema.apicr7imports.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pais")
public class Country extends RepresentationModel<Country> {
	
	
	@Id
	@Column(name = "paisid")
	Integer idCountry;

	@Column(name = "paisnome")
	String namePort;
	
	@Column(name = "paisname")
	String nameEng;
	
}
