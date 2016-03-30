package com.vaadin.devon.designImpl;

import com.vaadin.devon.dummyImpl.DummyChart;
import com.vaadin.devon.dummyImpl.DummyGrid;
import com.vaadin.devon.dummyImpl.DummyRest;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.UIScope;

@UIScope
public enum DashboardViewType {

	// right now i am using SpringViewProvider. so the only parameter will
	// impact the naviagtor is only the viewName
	DASHBOARD("Dummy Grid", DummyGrid.class, FontAwesome.HOME, true), REST("Real-Time Temperature", DummyChart.class,
			FontAwesome.FROWN_O, true), GRID1("Grid", DummyRest.class, FontAwesome.BAR_CHART, false);

	private final String viewName;
	private final Class<? extends View> viewClass;
	private final Resource icon;
	private final boolean stateful;

	private DashboardViewType(final String viewName, final Class<? extends View> viewClass, final Resource icon,
			final boolean stateful) {
		this.viewName = viewName;
		this.viewClass = viewClass;
		this.icon = icon;
		this.stateful = stateful;
	}

	public boolean isStateful() {
		return stateful;
	}

	public String getViewName() {
		return viewName;
	}

	public Class<? extends View> getViewClass() {
		return viewClass;
	}

	public Resource getIcon() {
		return icon;
	}

	public static DashboardViewType getByViewName(final String viewName) {
		DashboardViewType result = null;
		for (DashboardViewType viewType : values()) {
			if (viewType.getViewName().equals(viewName)) {
				result = viewType;
				break;
			}
		}
		return result;
	}

}
