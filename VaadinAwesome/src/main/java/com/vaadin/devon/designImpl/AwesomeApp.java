package com.vaadin.devon.designImpl;

import com.vaadin.devon.design.AwesomeAppDesign;
import com.vaadin.devon.dummyImpl.DummyHome;
import com.vaadin.devon.dummyImpl.DummyRest;
import com.vaadin.devon.entity.User;
import com.vaadin.devon.firstscreen.Default;
import com.vaadin.devon.util.CheckSession;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

public class AwesomeApp extends AwesomeAppDesign {

	private MenuItem settingsItem;
	private Navigator navigator;

	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private static final String STYLE_VISIBLE = "valo-menu-visible";

	private Label notificationsBadge;

	public AwesomeApp() {
		buildUserMenu();
		setupNavigator("Home", DummyHome.class);
		btn1.addClickListener(click -> {
			setupNavigator("Home", DummyHome.class);
			csslayout1.addStyleName("badgewrapper");
			btn1.setCaption("DashBoard "+"<span class=\"MENU_BADGE\">1</span>");
		});

		btn2.addClickListener(click -> {
			setupNavigator("Rest", DummyRest.class);
		});
		
		
	}

	private void setupNavigator(String urlName, Class view) {
		navigator = new Navigator(Default.getCurrent(), panel);

		navigator.addView(urlName, view);

		navigator.navigateTo(urlName);
	}

	private User getCurrentUser() {
		return CheckSession.get();
	}

	private void updateUserName() {
		User user = getCurrentUser();

		settingsItem.setText(user.getUsername());
	}

	private Component buildBadgeWrapper(final Component menuItemButton, final Component badgeLabel) {
		CssLayout dashboardWrapper = new CssLayout(menuItemButton);
		dashboardWrapper.addStyleName("badgewrapper");
		dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
		badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
		badgeLabel.setWidthUndefined();
		badgeLabel.setVisible(false);
		dashboardWrapper.addComponent(badgeLabel);
		System.out.println("----------------");
		return dashboardWrapper;
	}

	private void ss() {
		
			System.out.println("========");
			notificationsBadge = new Label();
			notificationsBadge.setId(NOTIFICATIONS_BADGE_ID);
			csslayout1.addComponent(buildBadgeWrapper(csslayout1, notificationsBadge));
		
	}

	private void buildUserMenu() {
		menuBar.addStyleName("user-menu");
		settingsItem = menuBar.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);
		updateUserName();
		settingsItem.addItem("Edit Profile", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				// ProfilePreferencesWindow.open(user, false);
				System.out.println("Edit Profile");
			}
		});
		settingsItem.addItem("Preferences", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				// ProfilePreferencesWindow.open(user, true);
				System.out.println("Preferences");
			}
		});
		settingsItem.addSeparator();
		settingsItem.addItem("Sign Out", new Command() {
			@Override
			public void menuSelected(final MenuItem selectedItem) {
				// DashboardEventBus.post(new UserLoggedOutEvent());
				System.out.println("Sign Out");
				CheckSession.signOut();
				Page.getCurrent().reload();
			}
		});
	}

}
