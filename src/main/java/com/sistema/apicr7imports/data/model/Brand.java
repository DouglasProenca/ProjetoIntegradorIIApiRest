package com.sistema.apicr7imports.data.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "marca")
public class Brand {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer brandId;
	
	@Column(name = "marca")
	String brandName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais")
	Country country;
	
	@Column(name = "data_criacao")
	LocalDate date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;

	@Override
	public String toString() {
		return brandId + ";" + brandName + ";" + country.getNamePort() + ";" +date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" + user.getUsername();
	}
	
}
