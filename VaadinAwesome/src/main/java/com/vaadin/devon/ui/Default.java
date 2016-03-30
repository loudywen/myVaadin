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
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

@SpringUI(path = "/")
@Theme("dashboard")
@Title("Real-Time Temperature Demo")
@Widgetset("MyAppWidgetset")

public class Default extends UI {

	private EventBus eventBus;

	@Autowired
	private SpringViewProvider viewProvider;
	
	  @Autowired
	  private DummyDefault dd;
	 

	@Override
	protected void init(VaadinRequest request) {

		/*if (CheckSession.isLoggedIn()) {
			setContent(new MainView());
			//getUI().getPage().setLocation("/mainview");
		} else {
			setContent(new LoginView1());

		}*/

		setupEventBus();
		 setContent(dd);
		/*
		 * VerticalLayout layout = new VerticalLayout(); Panel panel = new
		 * Panel();
		 * 
		 * layout.addComponent(panel); setContent(layout); Navigator nv = new
		 * Navigator(this, panel); nv.addProvider(viewProvider);
		 * nv.navigateTo("dummygrid");
		 */

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
