package com.vaadin.devon.dummyImpl;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.devon.dummy.DummyDefaultDesign;
import com.vaadin.devon.firstscreen.Default;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

@Theme("mytheme")
@Widgetset("MyAppWidgetset")
@Title("Dummy Default")
public class DummyDefault extends DummyDefaultDesign implements ViewChangeListener {

	private Navigator navigator;

	public DummyDefault() {
		setupNavigator("Chart",DummyChart.class);
		restBtn.setCaption("Chart");
		homeBtn.addClickListener(click -> {
			setupNavigator("Home",DummyHome.class);
		});

		restBtn.addClickListener(click -> {
			setupNavigator("Chart",DummyChart.class);
		});

	}

	private void setupNavigator(String urlName,  Class view) {
		navigator = new Navigator(Default.getCurrent(), panel);

		navigator.addView(urlName, view);

		navigator.navigateTo(urlName);
	}


	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
