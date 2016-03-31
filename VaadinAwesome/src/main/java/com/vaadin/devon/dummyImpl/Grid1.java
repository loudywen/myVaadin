package com.vaadin.devon.dummyImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.devon.dummy.GridDesign;
import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.devon.entity.User;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

@SpringView(name ="Grid")
@UIScope
@Theme("dashboard")
public class Grid1 extends GridDesign implements View {
	
	@Autowired
	@Qualifier("TemperatureRecordsImpl")
	private TemperatureRecordsDAO tRDAO;

	@Autowired
	private TemperatureRecords tr;

	@PostConstruct
	protected void init(){
		//setMargin(true);
		setSpacing(true);
		
		listTemp(null);
		grid.setSizeFull();
		grid.setColumnOrder("id","email","temperature","device","timeStamp");
		addComponent(grid);

	
	}
	

	private void listTemp(String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(
					new BeanItemContainer<TemperatureRecords>(TemperatureRecords.class, tRDAO.findAll()));
		} else {
			/*
			 * grid.setContainerDataSource( new
			 * BeanItemContainer<TemperatureRecords>(TemperatureRecords.class,
			 * tRDAO.findAll()));
			 */
		}
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
