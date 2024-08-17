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
	Integer brandId;
	
	@Column(name = "marca")
	String brandName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pais")
	Country country;
	
	@Column(name = "[date]")
	LocalDate date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	User user;
	
	/**
	 * @param id
	 */
	public Brand(Integer brandId) {
		this.brandId = brandId;
	}

	@Override
	public String toString() {
		return brandId + ";" + brandName + ";" + country.getNamePort() + ";" + new SimpleDateFormat("dd/MM/yyyy").format(date) + ";" + user.getUsername();
	}
	
}
