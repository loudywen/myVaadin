package com.vaadin.devon.dummyImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.devon.entity.User;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = "Dummy Grid")

@Widgetset("MyAppWidgetset")
@UIScope
public class DummyGrid extends VerticalLayout implements View {

	private Grid grid = new Grid();
	//private Table t = new Table();
	private TextField filter = new TextField();
	private Button addNewBtn = new Button();

	@Autowired
	@Qualifier("TemperatureRecordsImpl")
	private TemperatureRecordsDAO tRDAO;

	@Autowired
	private TemperatureRecords tr;

	@Override
	public void enter(ViewChangeEvent event) {

	}


	@PostConstruct
	public void init() {
		
		//setMargin(true);
		setSpacing(true);
		//setSizeFull();
		addComponent(toolBar());
	//	addComponent(buildTable());
		listTemp(null);
		grid.setSizeFull();
		
		grid.setColumnOrder("id","email","temperature","device","timeStamp");
		addComponent(grid);

	}

	private void addCompents(TextField filter2, Button addNewBtn2) {
		// TODO Auto-generated method stub

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

	private Component toolBar() {
		HorizontalLayout actions = new HorizontalLayout();
		actions.addComponent(filter);
		actions.addComponent(addNewBtn);
		actions.setSpacing(true);
		return actions;
	}


}
