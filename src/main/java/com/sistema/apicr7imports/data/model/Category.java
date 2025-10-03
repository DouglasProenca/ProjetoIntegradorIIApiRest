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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer categoryId;
	
	@Column(name = "nome")
	String categoryName;
	
	@Column(name = "data_criacao")
	LocalDate date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;

	@Override
	public String toString() {
		return String.join("",categoryId.toString() , ";", categoryName, ";", date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), ";", user.getUsername());
	}
}
