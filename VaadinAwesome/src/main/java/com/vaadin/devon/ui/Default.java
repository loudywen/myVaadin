package com.vaadin.devon.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.devon.designImpl.DashboardViewType;
import com.vaadin.devon.designImpl.LoginView1;
import com.vaadin.devon.designImpl.MainView;
import com.vaadin.devon.entity.User;
import com.vaadin.devon.event.LoginEvent;
import com.vaadin.devon.event.NavigationEvent;
import com.vaadin.devon.event.TempEvent;
import com.vaadin.devon.util.CheckSession;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path = "/")
@Theme("dashboard")
@Title("Real-Time Temperature Demo")
@Widgetset("MyAppWidgetset")

public class Default extends UI {

	public static final String ID = "dashboard-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private static final String STYLE_VISIBLE = "valo-menu-visible";
	private Label notificationsBadge;
	private Label reportsBadge;
	private MenuItem settingsItem;
	private ComponentContainer content;
	private DashboardViewType view;

	private Navigator nv;
	private EventBus eventBus;

	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {

		if (CheckSession.isLoggedIn()) {
			System.out.println("---------------------------Default-------------------- " + viewProvider);
			setContent(new MainView(viewProvider));

		} else {
			setContent(new LoginView1());
		}

		setupEventBus();
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
