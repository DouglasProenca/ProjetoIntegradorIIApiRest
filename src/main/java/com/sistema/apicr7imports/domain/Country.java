package com.sistema.apicr7imports.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "rc_pais")
public class Country extends RepresentationModel<Country> {
	
	@NonNull
	@Id
	@Column(name = "paisid")
	Integer idCountry;

	@Column(name = "paisnome")
	String namePort;
	
	@Column(name = "paisname")
	String nameEng;
	
}
