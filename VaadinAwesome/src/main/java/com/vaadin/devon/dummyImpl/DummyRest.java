package com.vaadin.devon.dummyImpl;

import com.vaadin.devon.dummy.DummyRestDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name="Dummy Rest")
public class DummyRest extends DummyRestDesign implements View{

	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
