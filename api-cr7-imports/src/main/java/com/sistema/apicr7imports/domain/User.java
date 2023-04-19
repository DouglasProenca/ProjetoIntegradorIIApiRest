package com.sistema.apicr7imports.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rc_user")
public class User implements Serializable{

	    private static final long serialVersionUID = 1L;
	    @Id
		private int id;
	    private String mail;
	    @Column(name = "mailPassword")
	    private String mailPassword;
	    @Column(name = "[user]")
	    private String user;
	    private String password;
	    @Column(name = "data")
	    private Date data;

	    public User() {
	    }

	    public User(int id, String mail, String mailPassword, String user, String password, Date date) {
	        this.id = id;
	        this.mail = mail;
	        this.mailPassword = mailPassword;
	        this.user = user;
	        this.password = password;
	        this.data = date;
	    }

	    public Date getDate() {
	        return data;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getMail() {
	        return mail;
	    }

	    public void setMail(String mail) {
	        this.mail = mail;
	    }

	    public String getMailPassword() {
	        return mailPassword;
	    }

	    public void setMailPassword(String mailPassword) {
	        this.mailPassword = mailPassword;
	    }

	    public String getUser() {
	        return user;
	    }

	    public void setUser(String user) {
	        this.user = user;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}

