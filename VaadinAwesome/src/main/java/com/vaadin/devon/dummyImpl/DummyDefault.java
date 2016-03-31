package com.vaadin.devon.dummyImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.devon.dummy.DummyDefaultDesign;
import com.vaadin.devon.ui.DefaultUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.navigator.SpringViewProvider;


public class DummyDefault extends DummyDefaultDesign implements View {

	private Navigator navigator;
	private SpringViewProvider viewProvider;


	public DummyDefault(SpringViewProvider svp) {
		this.viewProvider = svp;
		homeBtn.setCaption("Grid - CRUD Version");
		restBtn.setCaption("Live Temperature");
		setupNavigator("Real-Time Temperature");
		homeBtn.addClickListener(click -> {
			setupNavigator("Dummy Grid - CRUD Version");
			// toSpringView();
		});

		restBtn.addClickListener(click -> {
			setupNavigator("Real-Time Temperature");
		});

		System.out.println("-----------------------------------------------");
	}

	private void setupNavigator(String urlName) {
		Navigator navigator = new Navigator(DefaultUI.getCurrent(), panel);

		navigator.addProvider(viewProvider);
		navigator.navigateTo(urlName);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
