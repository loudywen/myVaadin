package com.vaadin.devon.dummyImpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.devon.boardcast.Broadcaster;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@SpringView(name = "Real-Time Temperature")
@UIScope

public class DummyChart extends AbstractVaadinChartExample implements View, Broadcaster.BroadcastListener {

	private double temp = 0;
	private double tempholder = 0;
	private int count = 0;
	volatile boolean shutdown = false;
	private SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	DummyTimelineSingleLine chart2;

	@PostConstruct
	public void init() {

		addComponent(getChart());
		addComponent(chart2.getChart());
		setResponsive(true);
		Broadcaster.register(this);

	}

	@Override
	public String getDescription() {
		return "Spline Updating Each 10 seconds";
	}

	@Override
	protected Component getChart() {

		final Random random = new Random();

		final Chart chart = new Chart();
		chart.setResponsive(true);
		final Configuration configuration = chart.getConfiguration();
		configuration.getChart().setType(ChartType.SPLINE);

		XAxis xAxis = configuration.getxAxis();
		xAxis.setType(AxisType.DATETIME);
		Date currentTime1 = new Date();

		String Date = new SimpleDateFormat("yyyy-MM-dd").format(currentTime1);
		configuration.getTitle().setText("Live temperature data - " + Date);

		xAxis.setTitle(new AxisTitle("Current Timestamp"));
		xAxis.setTickPixelInterval(50);

		YAxis yAxis = configuration.getyAxis();
		yAxis.setTitle(new AxisTitle("Temperature"));
		yAxis.setPlotLines(new PlotLine(0, 1, new SolidColor("#808080")));
	
		//configuration.getTooltip().setFormatter("");
		configuration.getTooltip().setEnabled(true);
		configuration.getLegend().setEnabled(false);

		final DataSeries series = new DataSeries();
		series.setPlotOptions(new PlotOptionsSpline());
		series.setName("Temperature");

		for (int i = -19; i <= 0; i++) {
			Date currentTime = new Date();
			Date date = null;
			Date diffTime = DateUtils.addSeconds(currentTime, i * 10);
			String updatedTime2 = new SimpleDateFormat("HH:mm:ss").format(diffTime);

			try {
				date = dt.parse(updatedTime2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("----------------------"+updatedTime2);

			series.add(new DataSeriesItem(date, temp));

		}
		runWhileAttached(chart, new Runnable() {

			@Override
			public void run() {
				Date currentTime = new Date();
				Date date = null;

				String updatedTime2 = new SimpleDateFormat("HH:mm:ss").format(currentTime);

				try {
					date = dt.parse(updatedTime2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				series.add(new DataSeriesItem(date, temp), true, true);
				if (tempholder == temp) {
					if (count > 7) {
						temp = 0;
						count = 0;
					} else {
						count++;
					}

				} else {
					tempholder = temp;
					count = 0;
				}
			}
		}, 10000, 10000);

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
