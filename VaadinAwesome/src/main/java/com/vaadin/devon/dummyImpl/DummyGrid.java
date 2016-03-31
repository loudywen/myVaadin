package com.vaadin.devon.dummyImpl;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.devon.db2repo.TempRepo;
import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = "Dummy Grid - CRUD Version")
@UIScope
public class DummyGrid extends VerticalLayout implements View {

	private Grid grid;
	private TextField filter = new TextField();
	private Button clearFilterTextBtn = new Button("Clear filter");

	@Autowired
	TempRepo tRepo;

	@Autowired
	private TemperatureRecords tr;

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@PostConstruct
	public void init() {
		grid = new Grid();
		setPrimaryStyleName("v-verticallayout");
		setSizeFull();
		setSpacing(true);

		addComponent(toolBar());

		listTemp(null);
		grid.setSizeFull();
		grid.setHeight("100%");
		grid.setStyleName(ValoTheme.TABLE_BORDERLESS);
		grid.setStyleName(ValoTheme.TABLE_COMPACT);
		grid.setStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		grid.setColumnOrder("id", "email", "temperature", "device", "timeStamp");

		addComponent(grid);
		setExpandRatio(grid, 1.0f);
		//

	}

	private void listTemp(String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(
					new BeanItemContainer<TemperatureRecords>(TemperatureRecords.class, (Collection<? extends TemperatureRecords>) tRepo.findAll()));
		} else {
			grid.setContainerDataSource(new BeanItemContainer<>(TemperatureRecords.class, tRepo.findByEmailStartingWith(text)));

		}
	}

	private Component toolBar() {
		HorizontalLayout actions = new HorizontalLayout();
	
		filter.setInputPrompt("filter by email...");
		filter.addTextChangeListener(e -> {
			grid.setContainerDataSource(
					new BeanItemContainer<>(TemperatureRecords.class, tRepo.findByEmailStartingWith(e.getText())));
		});

		clearFilterTextBtn.addClickListener(e -> {
			filter.clear();
			listTemp(null);
		});

		actions.setResponsive(true);
		actions.addComponent(filter);
	

		actions.addComponent(clearFilterTextBtn);
	
	
		
		
		actions.setSpacing(true);
	 actions.setMargin(true);
		return actions;
	}

}
