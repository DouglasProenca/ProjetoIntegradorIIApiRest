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

@Entity
@Data
@NoArgsConstructor
@Table(name = "cliente")
public class Client {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer clientId;
	
	@Column(name = "nome")
	String  clientName;
	
	String cpf;
	
	@Column(name = "data_criacao")
	LocalDate date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;
}
