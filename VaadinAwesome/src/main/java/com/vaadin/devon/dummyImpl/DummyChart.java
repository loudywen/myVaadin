package com.vaadin.devon.dummyImpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import com.vaadin.addon.charts.Chart;

import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.devon.boardcast.Broadcaster;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.devon.dummy.DummyChartDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;

public class DummyChart extends AbstractVaadinChartExample implements View, Broadcaster.BroadcastListener {

	private double temp = 0;
	private double tempholder = 0;
	private int count = 0;
	volatile boolean shutdown = false;
	DummyTimelineSingleLine chart2 = new DummyTimelineSingleLine();
	public DummyChart() {
		
		addComponent(getChart());
		addComponent(chart2.getChart());
		Broadcaster.register(this);
	}

	@Override
	public String getDescription() {
		return "Spline Updating Each Seconds";
	}

	@Override
	protected Component getChart() {
		final Random random = new Random();

		final Chart chart = new Chart();
		
		final Configuration configuration = chart.getConfiguration();
		configuration.getChart().setType(ChartType.SPLINE);
		configuration.getTitle().setText("Live temperature data");

		XAxis xAxis = configuration.getxAxis();
		xAxis.setType(AxisType.DATETIME);
		xAxis.setTitle(new AxisTitle("Current timestamp"));
		xAxis.setTickPixelInterval(150);

		YAxis yAxis = configuration.getyAxis();
		yAxis.setTitle(new AxisTitle("Temperature"));
		yAxis.setPlotLines(new PlotLine(0, 1, new SolidColor("#808080")));

		configuration.getTooltip().setEnabled(true);
		configuration.getLegend().setEnabled(false);

		final DataSeries series = new DataSeries();
		series.setPlotOptions(new PlotOptionsSpline());
		series.setName("Temperature");
		for (int i = -19; i <= 0; i++) {
			series.add(new DataSeriesItem(System.currentTimeMillis() + i * 1000, temp));
		}
		runWhileAttached(chart, new Runnable() {

			@Override
			public void run() {
				final long x = System.currentTimeMillis();
				// final double y = random.nextDouble();

				series.add(new DataSeriesItem(x, temp), true, true);
				if(tempholder == temp){
					if(count > 7){
						temp = 0;
						count = 0;
					}else{
						count++;
					}
					
				}else{
					tempholder = temp;
					count = 0;
					}
			}
		}, 1000, 1000);

		
		configuration.setSeries(series);

		chart.drawChart(configuration);
		return chart;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveBroadcast(String message) {
		String[] holder = message.split(",");
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		temp = Double.parseDouble(holder[0]);
		// System.out.println("temp recieved from boardcaster: " + temp);
		
	}

}
