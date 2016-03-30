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
@Theme("dashboard")
@Widgetset("MyAppWidgetset")
@UIScope
public class DummyGrid extends VerticalLayout implements View {

	private Grid grid = new Grid();
	private Table t = new Table();
	private TextField filter = new TextField();
	private Button addNewBtn = new Button();
	private User usr = new User(null);

	@Autowired
	@Qualifier("TemperatureRecordsImpl")
	private TemperatureRecordsDAO tRDAO;

	@Autowired
	private TemperatureRecords tr;

	@Override
	public void enter(ViewChangeEvent event) {

	}

	/*
	 * public DummyGrid() { grid = new Grid(); grid.setSizeFull();
	 * setMargin(true); grid.addColumn("id", Long.class);
	 * grid.addColumn("email", String.class); grid.addColumn("temperature",
	 * Double.class); addComponent(grid);
	 * 
	 * System.out.println("===================" + tRDAO);
	 * 
	 * }
	 */

	@PostConstruct
	public void init() {

		setMargin(true);
		setSpacing(true);

		addComponent(toolBar());
		addComponent(buildGrid());

	}

	private void addCompents(TextField filter2, Button addNewBtn2) {
		// TODO Auto-generated method stub

	}

	private void listTemp(String text) {
		if (StringUtils.isEmpty(text)) {
			t.setContainerDataSource(
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

	private Component buildGrid() {

		t.setColumnReorderingAllowed(true);
		t.setColumnCollapsingAllowed(true);
		t.setResponsive(true);
		// table.setSelectionMode(SelectionMode.SINGLE);

		t.addStyleName(ValoTheme.TABLE_BORDERLESS);
		t.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		t.addStyleName(ValoTheme.TABLE_COMPACT);
		t.setWidth("100%");
		listTemp(null);
		t.setVisibleColumns(new Object[] { "id", "email", "temperature", "device", "timeStamp" });
		// t.setHeight("100%");
		t.setColumnHeaders(new String[] { "ID", "Email", "Temperature", "Device", "TimeStamp" });
		// grid.setColumns("id", "email", "temperature", "device", "timeStamp");

		return t;

	}

	private Component test() {
		List<User> people = new ArrayList<User>();
		grid.setPrimaryStyleName("v-grid");
		grid.addStyleName(ValoTheme.TABLE_BORDERLESS);
		grid.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		grid.addStyleName(ValoTheme.TABLE_COMPACT);
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));
		people.add(new User("asf"));

		// Have a container of some type to contain the data
		BeanItemContainer<User> container = new BeanItemContainer<User>(User.class, people);

		// Create a grid bound to the container
		Grid grid = new Grid(container);
		grid.setColumnOrder("username");
		return grid;
	}

}
