package com.vaadin.devon.event;

import com.vaadin.devon.entity.User;

public class TempEvent {
	
	String temp;

	public TempEvent(String str) {
		this.temp = str;
	}

	public String getTemp() {
		return temp;
	}
}
