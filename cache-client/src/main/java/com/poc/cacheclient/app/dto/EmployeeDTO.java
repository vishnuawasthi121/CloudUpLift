package com.poc.cacheclient.app.dto;

public class EmployeeDTO {

	private int id;

	private String message;

	public EmployeeDTO(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", message=" + message + "]";
	}

}
