package com.portal.auto.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	@NotNull
	private String userName;
	
	@Column(name="role")
	@NotNull
	private String role;
	
	@Column(name="password")
	@NotNull
	private String password;
	
	@OneToMany(mappedBy="user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserToken> userToken = new HashSet<UserToken>(0);
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Set<UserToken> getUserToken() {
		return userToken;
	}

	public void setUserToken(Set<UserToken> userToken) {
		this.userToken = userToken;
	}
}
