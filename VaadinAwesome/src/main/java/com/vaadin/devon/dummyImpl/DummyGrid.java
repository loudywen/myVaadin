package com.vaadin.devon.dummyImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "dummygrid")

public class DummyGrid extends VerticalLayout implements View {

	private Grid grid;
	@Autowired
	@Qualifier("TemperatureRecordsImpl")
	private TemperatureRecordsDAO tRDAO;

	@Autowired
	private TemperatureRecords tr;

	@Override
	public void enter(ViewChangeEvent event) {
		grid.setContainerDataSource(
				new BeanItemContainer<TemperatureRecords>(TemperatureRecords.class, tRDAO.findAll()));
		addComponent(grid);
	

	}

	@PostConstruct
	protected void init() {

	
		grid = new Grid();
		grid.setSizeFull();
		grid.addColumn("id", Long.class);
		grid.addColumn("email", String.class);
		grid.addColumn("temperature",Double.class);
		//grid.addColumn("Temperature", Double.class);

	/*	tRDAO.findAll();
		System.out.println("DDDDDDDDDDDDDDDDDDDDD");
		Notification.show(tRDAO.toString());*/

	}

}
