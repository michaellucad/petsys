package com.lu.model;

import java.util.List;

public class Response {
	private List<AppError> errors;

	public List<AppError> getErrors() {
		return errors;
	}

	public void setErrors(List<AppError> errors) {
		this.errors = errors;
	}
}
