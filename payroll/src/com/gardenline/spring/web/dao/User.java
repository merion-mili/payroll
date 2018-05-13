package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.gardenline.spring.web.validation.ValidEmail;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362437768854142524L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 8, max = 15, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Pattern(regexp = "^\\w{8,}$", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "username")
	private String username;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 8, max = 60, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "name")
	private String name;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Pattern(regexp = "^\\S+$", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 8, max = 60, groups = { FormValidationGroup.class })
	@Column(name = "password")
	private String password;

	@ValidEmail(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	private String email;

	@NotNull(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Type(type = "boolean")
	private boolean enabled = false;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	private String authority;

	public User() {

	}

	public User(String username, String name, String password, String email,
			boolean enabled, String authority) {

		this.username = username;
		this.name = name;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public User(int id, String username, String name, String password,
			String email, boolean enabled, String authority) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
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
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name
				+ ", password=" + password + ", email=" + email + ", enabled="
				+ enabled + ", authority=" + authority + "]";
	}

	
}
