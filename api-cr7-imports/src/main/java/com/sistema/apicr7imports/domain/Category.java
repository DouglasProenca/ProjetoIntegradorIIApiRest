package com.sistema.apicr7imports.domain;

import java.io.Serializable;
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

import com.sistema.apicr7imports.domain.Dto.UserDTO;

@Entity
@Table(name = "rc_categoria")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "[data]")
	private Date data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "[user]")
	private UserDTO user;

	public Category() {
		
	}
	

	/**
	 * @param id
	 * @param categoria
	 * @param data
	 * @param user
	 */
	public Category(Long id, String categoria, Date data, UserDTO user) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.data = data;
		this.user = user;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return id + ";" + categoria + ";" + new SimpleDateFormat("dd/MM/yyyy").format(data) + ";" + user.getUserName();
	}
}
