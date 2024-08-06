package com.sistema.apicr7imports.data.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
@Table(name = "rc_marca")
public class Brand {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "marca")
	String marca;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais")
	Country country;
	
	@Column(name = "[date]")
	LocalDate data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	User user;
	
	/**
	 * @param id
	 */
	public Brand(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + ";" + marca + ";" + country.getNamePort() + ";" + new SimpleDateFormat("dd/MM/yyyy").format(data) + ";" + user.getUsername();
	}
	
}
