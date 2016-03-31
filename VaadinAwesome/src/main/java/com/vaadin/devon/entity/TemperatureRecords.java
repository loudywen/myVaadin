package com.vaadin.devon.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Entity
@Component
public class TemperatureRecords {
	@Id
	 @GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	private String email;
	private String device;
	private double temperature;
	private Timestamp timeStamp;

	public TemperatureRecords(long id, String email, double temperature, String device, Timestamp timeStamp) {
		super();
		this.id = id;
		this.email = email;
		this.temperature = temperature;
		this.device = device;
		this.timeStamp = timeStamp;
	}

	public TemperatureRecords() {

	}

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
