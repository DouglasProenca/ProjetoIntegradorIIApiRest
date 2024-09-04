package com.sistema.apicr7imports.data.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produto")
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
	 
	@Column(name = "data_criacao")
	LocalDate data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	Category category;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	List<ProductImage> images;
	
	@Override
	public String toString() {
		return id + ";" + nome + ";" + brand.getBrandName() + ";" + valor + ";" + quantidade + ";" + category.getCategoryName() + ";" + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";"
				+ user.getUsername();
	}

}
