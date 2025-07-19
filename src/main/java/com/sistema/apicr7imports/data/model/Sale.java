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

@Entity
@Table(name = "venda")
public class Sale {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer saleId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente")
	Client client;
	
	Double total;

	@Column(name = "data_criacao")
	LocalDate date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;
}
