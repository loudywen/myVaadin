package com.vaadin.devon.designImpl;

import com.google.common.eventbus.EventBus;
import com.vaadin.devon.entity.User;
import com.vaadin.devon.event.LoginEvent;
import com.vaadin.devon.ui.Default;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")

public class LoginView1 extends VerticalLayout {

	public LoginView1() {
		setSizeFull();

		Component loginForm = buildLoginForm();
		addComponent(loginForm);
		setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
/*
		Notification notification = new Notification("Welcome to Dashboard Demo");
		notification.setDescription(
				"<span>This application is not real, it only demonstrates an application built with the <a href=\"https://vaadin.com\">Vaadin framework</a>.</span> <span>No username or password is required, just click the <b>Sign In</b> button to continue.</span>");
		notification.setHtmlContentAllowed(true);
		notification.setStyleName("tray dark small closable login-help");
		notification.setPosition(Position.BOTTOM_CENTER);
		notification.setDelayMsec(5000);
		notification.show(Page.getCurrent());*/
	}

	private Component buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		Responsive.makeResponsive(loginPanel);
		loginPanel.addStyleName("login-panel");

		loginPanel.addComponent(buildLabels());
		loginPanel.addComponent(buildFields());
		loginPanel.addComponent(new CheckBox("Remember me", false));
		return loginPanel;
	}

	private Component buildFields() {
		HorizontalLayout fields = new HorizontalLayout();
		fields.setSpacing(true);
		fields.addStyleName("fields");

		final TextField username = new TextField("Username");
		username.setIcon(FontAwesome.USER);
		username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final PasswordField password = new PasswordField("Password");
		password.setIcon(FontAwesome.LOCK);
		password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final Button signin = new Button("Sign In");
		signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signin.setClickShortcut(KeyCode.ENTER);
		signin.focus();

		fields.addComponents(username, password, signin);
		fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

		signin.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				if (!username.isEmpty() && !password.isEmpty()) {

					if (username.getValue().equals("admin") ||username.getValue().equals("admin1")) {
						User u = new User(username.getValue());
						EventBus eventBus = new EventBus();
						eventBus.register(new Default());
						eventBus.post(new LoginEvent(u));
					} else {
						Notification notification = new Notification("Login failed, Please enter correct Username.");
						notification.setStyleName("tray dark small closable login-help");
						notification.setPosition(Position.BOTTOM_CENTER);
						notification.setDelayMsec(1000);
						notification.show(Page.getCurrent());
						username.focus();
					}

				} else {
					Notification notification = new Notification("Login failed.", "Hint: use any non-empty strings");
					notification.setStyleName("tray dark small closable login-help");
					notification.setPosition(Position.BOTTOM_CENTER);
					notification.setDelayMsec(1000);
					notification.show(Page.getCurrent());
					username.focus();
					username.focus();
				}
			}
		});
		return fields;
	}

	private Component buildLabels() {
		CssLayout labels = new CssLayout();
		labels.addStyleName("labels");

		Label welcome = new Label("Welcome");
		welcome.setSizeUndefined();
		welcome.addStyleName(ValoTheme.LABEL_H4);
		welcome.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(welcome);

		Label title = new Label("Real-Time Dashboard");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName(ValoTheme.LABEL_LIGHT);
		labels.addComponent(title);
		return labels;
	}

}
