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
@Table(name = "rc_produto")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String nome;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marca")
	Brand brand;

	Double valor;

	Integer quantidade;
	
	Boolean ativo;
	 
	@Column(name = "[date]")
	LocalDate data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	User user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[categoria]")
	Category category;

	@Override
	public String toString() {
		return id + ";" + nome + ";" + brand.getBrandName() + ";" + valor + ";" + quantidade + ";" + category.getCategoryName() + ";" + new SimpleDateFormat("dd/mm/yyyy").format(data) + ";"
				+ user.getUsername();
	}

}
