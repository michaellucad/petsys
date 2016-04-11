package com.lu.model;

import java.io.Serializable;

public class AppError implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1471192692503061152L;
	private String code;
	private String message;
	private Throwable exception;
	public AppError(){}
	public AppError(String code, String message, Throwable exception) {
		this.code = code;
		this.message = message;
		this.exception = exception;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	
}
