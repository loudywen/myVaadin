package com.vaadin.devon.designImpl;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/*
 * Dashboard MainView is a simple HorizontalLayout that wraps the menu on the
 * left and creates a simple container for the navigator on the right.
 */
@SuppressWarnings("serial")
@SpringView(name="mainview")
public class MainView extends HorizontalLayout implements View  {
	
	
	//@PostConstruct
    public  MainView(SpringViewProvider spv) {
    	System.out.println("---------------------------MainView-------------------- " + spv);
        setSizeFull();
        addStyleName("mainview");

        addComponent(new DashboardMenu(spv));

       ComponentContainer content = new CssLayout();
      //  Panel content = new Panel();

        
       content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);

        new DashboardNavigator(content,spv);
       
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
