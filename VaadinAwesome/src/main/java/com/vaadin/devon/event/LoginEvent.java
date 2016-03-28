package com.vaadin.devon.event;

import com.vaadin.devon.entity.User;

public class LoginEvent {
	private User user;

	public LoginEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
