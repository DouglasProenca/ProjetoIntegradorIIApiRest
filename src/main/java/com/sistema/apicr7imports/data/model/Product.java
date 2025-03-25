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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productId;

	@Column(name = "nome")
	String productName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marca")
	Brand brand;

	@Column(name = "valor")
	Double price;

	@Column(name = "quantidade")
	Integer amount;
	
	@Column(name = "ativo")
	Boolean enabled;
	 
	@Column(name = "data_criacao")
	LocalDate date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	User user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	Category category;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	List<ProductImage> images;
	
	@Override
	public String toString() {
		return productId + ";" + productName + ";" + brand.getBrandName() + ";" + price + ";" + amount + ";" + category.getCategoryName() + ";" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";"
				+ user.getUsername();
	}

}
