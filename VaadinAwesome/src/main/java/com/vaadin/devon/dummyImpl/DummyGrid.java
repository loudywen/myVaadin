package com.vaadin.devon.dummyImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "Dummy Grid")

@UIScope
public class DummyGrid extends VerticalLayout implements View {

	private Grid grid;
	@Autowired
	@Qualifier("TemperatureRecordsImpl")
	private TemperatureRecordsDAO tRDAO;

	@Autowired
	private TemperatureRecords tr;

	@Override
	public void enter(ViewChangeEvent event) {

	}

	/*public DummyGrid() {
		grid = new Grid();
		grid.setSizeFull();
		setMargin(true);
		grid.addColumn("id", Long.class);
		grid.addColumn("email", String.class);
		grid.addColumn("temperature", Double.class);
		addComponent(grid);

		System.out.println("===================" + tRDAO);

	}*/

	@PostConstruct
	public void init() {
		grid = new Grid();
		grid.setSizeFull();
		setMargin(true);
		grid.addColumn("id", Long.class);
		grid.addColumn("email", String.class);
		grid.addColumn("temperature", Double.class);
		addComponent(grid);
		grid.setContainerDataSource(
				new BeanItemContainer<TemperatureRecords>(TemperatureRecords.class, tRDAO.findAll()));
	}

}
