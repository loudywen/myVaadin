package com.vaadin.devon.dummyImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.devon.dummy.DummyDefaultDesign;
import com.vaadin.devon.firstscreen.Default;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.navigator.SpringViewProvider;

@Theme("mytheme")
@Widgetset("MyAppWidgetset")
@Title("Dummy Default")

public class DummyDefault extends DummyDefaultDesign implements ViewChangeListener {


	private Navigator navigator;


	@PostConstruct
	private void init() {
		//setupNavigator("Chart",DummyChart.class);
		//toSpringView();
		restBtn.setCaption("Chart");
		homeBtn.addClickListener(click -> {
			// setupNavigator("Home",DummyGrid.class);
		//	toSpringView();
		});

		restBtn.addClickListener(click -> {
			setupNavigator("Chart", DummyChart.class);
		});
		
		System.out.println("-----------------------------------------------");
	}

	private void setupNavigator(String urlName, Class view) {
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
