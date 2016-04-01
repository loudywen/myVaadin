package com.vaadin.devon.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.devon.designImpl.LoginView1;
import com.vaadin.devon.designImpl.MainView;
import com.vaadin.devon.dummyImpl.DummyDefault;
import com.vaadin.devon.event.LoginEvent;
import com.vaadin.devon.event.NavigationEvent;
import com.vaadin.devon.event.TempEvent;
import com.vaadin.devon.util.CheckSession;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

@SpringUI(path = "/")
@Theme("dashboard")
@Title("Real-Time Temperature Demo")
@Widgetset("MyAppWidgetset")

public class DefaultUI extends UI {
	
	private Navigator nv;
	private EventBus eventBus;

	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
		
		UI.getCurrent().getPage().setUriFragment("Login");

		if (CheckSession.isLoggedIn()) {
			//System.out.println("---------------------------DefaultUI-------------------- " + viewProvider);
			setContent(new MainView(viewProvider));

		} else {
			setContent(new LoginView1());
		}

		setupEventBus();
		
		//testing
		//setContent(new DummyDefault(viewProvider));
	}

	@Subscribe
	public void userLoggedIn(LoginEvent event) {
		CheckSession.set(event.getUser());
		System.out.println("LoginEvent triggered");

		// setContent(new DummyDefault());

	}

	@Subscribe
	public void navigateTo(NavigationEvent view) {
		getNavigator().navigateTo(view.getViewName());
	}

	@Subscribe
	public void sendTemp(TempEvent event) {

		// System.out.println("default temp: "+ event.getTemp()+"
		// dddddddddddddddddd+ "+UI.getCurrent());

	}

	private void setupEventBus() {
		eventBus = new EventBus((throwable, subscriberExceptionContext) -> {
			// log error
			throwable.printStackTrace();
		});
		eventBus.register(this);
	}

}
