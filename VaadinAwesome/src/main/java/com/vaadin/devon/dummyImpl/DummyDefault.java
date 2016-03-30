package com.vaadin.devon.dummyImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.devon.dummy.DummyDefaultDesign;
import com.vaadin.devon.ui.Default;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;

@Theme("mytheme")
@Widgetset("MyAppWidgetset")
@Title("Dummy Default")

public class DummyDefault extends DummyDefaultDesign implements ViewChangeListener {

	private Navigator navigator;
	@Autowired
	private DummyGrid dg;

	@PostConstruct
	private void init() {
		setupNavigator("Chart", DummyChart.class);
		// toSpringView();
		restBtn.setCaption("Chart");
		homeBtn.addClickListener(click -> {
			setupNavigator("Home", DummyGrid.class);
			// toSpringView();
		});

		restBtn.addClickListener(click -> {
			setupNavigator("Chart", DummyChart.class);
		});

		System.out.println("-----------------------------------------------");
	}

	private void setupNavigator(String urlName, Class view) {
		Navigator navigator = new Navigator(Default.getCurrent(), panel);

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
