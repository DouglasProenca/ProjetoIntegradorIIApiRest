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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rc_categoria")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer categoryId;
	
	@Column(name = "categoria")
	String categoryName;
	
	@Column(name = "[data]")
	LocalDate date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	User user;

	
	/**
	 * @param categoryId
	 */
	public Category(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return categoryId + ";" + categoryName + ";" + new SimpleDateFormat("dd/MM/yyyy").format(date) + ";" + user.getUsername();
	}
}
