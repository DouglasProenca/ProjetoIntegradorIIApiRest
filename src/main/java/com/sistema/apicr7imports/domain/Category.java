package com.sistema.apicr7imports.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rc_categoria")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String categoria;
	
	@Column(name = "[data]")
	Date data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	User user;

	
	/**
	 * @param id
	 */
	public Category(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + ";" + categoria + ";" + new SimpleDateFormat("dd/MM/yyyy").format(data) + ";" + user.getUsername();
	}
}
