package com.vaadin.devon.dummyImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.themes.ValoLightTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractVaadinChartExample extends VerticalLayout implements ChartExample {

	/**
	 * Runs given task repeatedly until the reference component is attached
	 *
	 * @param component
	 * @param task
	 * @param interval
	 * @param initialPause
	 *            a timeout after tas is started
	 */
	public static void runWhileAttached(final Component component, final Runnable task, final int interval,
			final int initialPause) {
		// Until reliable push available in our demo servers
		UI.getCurrent().setPollInterval(interval);

		final Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(initialPause);
					while (component.getUI() != null) {
						Future<Void> future = component.getUI().access(task);
						future.get();
						//System.out.println("--------------" + future);
						Thread.sleep(interval);
					}
				} catch (InterruptedException e) {
					System.out.println("-----------InterruptedException: "+e.getMessage());
				} catch (ExecutionException e) {
					System.out.println("-----------ExcutionException: "+e.getMessage());
				} catch (com.vaadin.ui.UIDetachedException e) {
					System.out.println("-----------Vaadin UIDetacheException: "+ e.getMessage());
				} catch (Exception e) {
					System.out.println("---------Exception: "+e.getMessage());
				}
				System.out.println("------------ " + Thread.currentThread().getName() + " Stop------------");
			}
		};
		thread.start();
	}

	private final VerticalLayout content;

	public AbstractVaadinChartExample() {
		content = this;
		content.setSizeFull();
		content.setMargin(true);
	}

	protected abstract Component getChart();

	@Override
	public void attach() {
		super.attach();
		setup();
	}

	@Override
	public Component getWrappedComponent() {
		setup();
		content.getComponent(0).setSizeFull();
		return content;
	}

	protected void setup() {
		if (content.getComponentCount() == 0) {
			final Component map = getChart();
			content.addComponent(map);
			content.setExpandRatio(map, 1);
		}
	}

	protected Color[] getThemeColors() {
		Theme theme = ChartOptions.get().getTheme();
		return (theme != null) ? theme.getColors() : new ValoLightTheme().getColors();
	}

	protected Theme getCurrentTheme() {
		Theme theme = ChartOptions.get().getTheme();
		return (theme != null) ? theme : new ValoLightTheme();
	}
}
