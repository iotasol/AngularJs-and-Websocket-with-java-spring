package com.demo.model.role;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.demo.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "tb_role")
@Scope("prototype")
@Component
public class Role {

	public static final Integer ROLE_ALL = 1;
	public static final Integer ROLE_USER = 2;
	public static final Integer ROLE_ADMIN = 3;

	public static final long MAX_ROLE = 3;
	public static final long MIN_ROLE = 1;

	public static final String AUTHORITY_ADMIN = "AUTHORITY_ADMIN";
	public static final String AUTHORITY_USER = "AUTHORITY_USER";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JsonIgnore
	private User user;

	private Integer role;

	public Role() {
		super();
	}

	public Role(User user, Integer role) {
		this.user = user;
		this.role = role;
	}

	public static Role createAdminRole(User user) {
		return new Role(user, ROLE_ALL);
	}

	public static Role createUserRole(User user) {
		return new Role(user, ROLE_USER);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public List<GrantedAuthority> getGrantedAuthorities() {
		if (role.equals(ROLE_ALL)) {
			return AuthorityUtils.createAuthorityList(AUTHORITY_ADMIN,
					AUTHORITY_USER);
		}
		if (role.equals(ROLE_USER)) {
			return AuthorityUtils.createAuthorityList(AUTHORITY_USER);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
