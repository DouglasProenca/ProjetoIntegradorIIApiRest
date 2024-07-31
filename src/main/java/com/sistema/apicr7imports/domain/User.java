package com.sistema.apicr7imports.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rc_user")
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "[user]", unique = true)
	String userName;
	
	String mail;
	
	String password;
	
	@Column(name = "account_non_expired")
	Boolean accountNonExpired;
	
	@Column(name = "account_non_locked")
	Boolean accountNonLocked;
	
	@Column(name = "credentials_non_expired")
	Boolean credentialsNonExpired;
	
	Boolean enabled;
	
	@Column(name = "mailpassword")
	String mailPassword;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rc_user_permission", joinColumns = { @JoinColumn (name = "id_user") },
			inverseJoinColumns = { @JoinColumn (name = "id_permission")})
	List<Permission> permissions;
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permission permission : this.permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}
	
	public String getUserName() {
		return this.userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, id, mail, mailPassword,
				password, permissions, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accountNonExpired, other.accountNonExpired)
				&& Objects.equals(accountNonLocked, other.accountNonLocked)
				&& Objects.equals(credentialsNonExpired, other.credentialsNonExpired)
				&& Objects.equals(enabled, other.enabled) && Objects.equals(id, other.id)
				&& Objects.equals(mail, other.mail) && Objects.equals(mailPassword, other.mailPassword)
				&& Objects.equals(password, other.password) && Objects.equals(permissions, other.permissions)
				&& Objects.equals(userName, other.userName);
	}
}