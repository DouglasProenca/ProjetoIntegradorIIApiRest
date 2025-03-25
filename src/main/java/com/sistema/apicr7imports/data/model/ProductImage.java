package com.sistema.apicr7imports.data.model;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produto_imagem")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto")
	Product product;
	
	@Column(name = "imagem")
	byte[] image;
	
	@Column(name = "data_criacao")
	LocalDate date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;
	
}
