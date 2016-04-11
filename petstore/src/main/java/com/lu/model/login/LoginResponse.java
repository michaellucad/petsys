package com.lu.model.login;

import java.util.List;

import com.lu.model.Response;

public class LoginResponse extends Response {
	private List<String> roles;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
