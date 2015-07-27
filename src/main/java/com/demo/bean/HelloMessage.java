package com.demo.bean;

public class HelloMessage {
	private String message;
	private int id;

	public HelloMessage() {

	}

	public HelloMessage(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
