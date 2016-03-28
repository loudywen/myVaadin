package com.vaadin.devon.entity;


import java.sql.Timestamp;

public class TemperatureRecords {

	private long id;
	private String email;
	private double temperature;
	private String device;
	private Timestamp timeStamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timestamp2) {
		this.timeStamp = timestamp2;
	}

	@Override
	public String toString() {
		return "TemperatureRecords [id=" + id + ", email=" + email + ", temperature=" + temperature + ", device="
				+ device + ", timeStamp=" + timeStamp + "]";
	}
}
