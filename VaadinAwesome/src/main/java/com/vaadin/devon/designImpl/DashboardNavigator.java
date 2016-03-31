package com.vaadin.devon.designImpl;

import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class DashboardNavigator extends Navigator {

	// Provide a Google Analytics tracker id here
	//private static final String TRACKER_ID = null;// "UA-658457-6";
	//private GoogleAnalyticsTracker tracker;

	private static final DashboardViewType ERROR_VIEW = DashboardViewType.DASHBOARD;
	private ViewProvider errorViewProvider;
	private SpringViewProvider spv;

	public DashboardNavigator(final ComponentContainer content, SpringViewProvider spv) {
		super(UI.getCurrent(), content);
/*
		String host = getUI().getPage().getLocation().getHost();
		if (TRACKER_ID != null && host.endsWith("demo.vaadin.com")) {
			initGATracker(TRACKER_ID);
		}*/
		
		
		this.spv = spv;
		System.out.println("---------------------------DashboardNavigator-------------------- " + spv);
		initViewChangeListener();
		initViewProviders(spv);

	}

/*	private void initGATracker(final String trackerId) {
		tracker = new GoogleAnalyticsTracker(trackerId, "demo.vaadin.com");

		// GoogleAnalyticsTracker is an extension add-on for UI so it is
		// initialized by calling .extend(UI)
		tracker.extend(UI.getCurrent());
	}*/

	private void initViewChangeListener() {
		addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(final ViewChangeEvent event) {
				// Since there's no conditions in switching between the views
				// we can always return true.
				return true;
			}

			@Override
			public void afterViewChange(final ViewChangeEvent event) {
				DashboardViewType view = DashboardViewType.getByViewName(event.getViewName());
				// Appropriate events get fired after the view is changed.
				/*
				 * DashboardEventBus.post(new PostViewChangeEvent(view));
				 * DashboardEventBus.post(new BrowserResizeEvent());
				 * DashboardEventBus.post(new CloseOpenWindowsEvent());
				 */
			}
		});
	}

	private void initViewProviders(SpringViewProvider spv) {
		// A dedicated view provider is added for each separate view type
		for (final DashboardViewType viewType : DashboardViewType.values()) {
			
			//I am using SpringViewProvider for this applicaion .so I comment out this part. and only add the spv to addProvider()
			
			/*ViewProvider viewProvider = new ClassBasedViewProvider(viewType.getViewName(), viewType.getViewClass()) {

				// This field caches an already initialized view instance if the
				// view should be cached (stateful views).
				private View cachedInstance;
				//private SpringViewProvider svp;
				@Override
				public View getView(final String viewName) {
					View result = null;
					if (viewType.getViewName().equals(viewName)) {
						if (viewType.isStateful()) {
							// Stateful views get lazily instantiated
							if (cachedInstance == null) {
								cachedInstance = super.getView(viewType.getViewName());
							}
							result = cachedInstance;
						} else {
							// Non-stateful views get instantiated every time
							// they're navigated to
							result = super.getView(viewType.getViewName());
						}
					}
					return result;
				}
			};

			if (viewType == ERROR_VIEW) {
				errorViewProvider = viewProvider;
			}*/

			if (viewType == ERROR_VIEW) {
				errorViewProvider = spv;
			}
			addProvider(spv);
		}

		setErrorProvider(new ViewProvider() {
			@Override
			public String getViewName(final String viewAndParameters) {
				return ERROR_VIEW.getViewName();
			}

			@Override
			public View getView(final String viewName) {
				return errorViewProvider.getView(ERROR_VIEW.getViewName());
			}
		});
	}
}
