package com.vaadin.devon.dummyImpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.devon.boardcast.Broadcaster;
import com.vaadin.devon.db2repo.TempRepo;
import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@SpringView(name="DummyTimelineSingleLine")
@UIScope
public class DummyTimelineSingleLine extends AbstractVaadinChartExample implements View, Broadcaster.BroadcastListener {

	private double temp = 0;
	private double tempholder = 0;
	private int count = 0;
	
	private SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");


	@Autowired
	TempRepo tRepo;
	
	@Autowired
	private TemperatureRecords tr;

	@PostConstruct
	protected void init() {
		
		Broadcaster.register(this);
	}

	@Override
	public String getDescription() {
		return "Single line chart with timeline";
	}

	@Override
	protected Component getChart() {
		
		final Chart chart = new Chart();
		chart.setResponsive(true);
		chart.setHeight("450px");
		chart.setWidth("100%");
		chart.setTimeline(true);

		Configuration configuration = chart.getConfiguration();
		configuration.getTitle().setText("Time line whatever");
		
		

		DataSeries series = new DataSeries();
		/*
		 * for(StockPrices.PriceData data : StockPrices.fetchAaplPrice()) {
		 * DataSeriesItem item = new DataSeriesItem();
		 * item.setX(data.getDate()); item.setY(data.getPrice());
		 * dataSeries.add(item); }
		 */
		
		Iterable<TemperatureRecords> t = tRepo.findAll();
		//XAxis xAxis = configuration.getxAxis();
		
		//xAxis.setType(AxisType.DATETIME);
		String dateTime = null;
		Date date = null;
		for (TemperatureRecords t1 : t) {
		/*	dateTime= new SimpleDateFormat("HH:mm:ss").format(t1.getTimeStamp());
			try {
				date = dt.parse(dateTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			series.add(new DataSeriesItem(t1.getTimeStamp(), t1.getTemperature()));
		}

	/*	runWhileAttached(chart, new Runnable() {

			@Override
			public void run() {
				final long x = System.currentTimeMillis();
				// final double y = random.nextDouble();

				series.add(new DataSeriesItem(x, temp), true, true);
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
		}, 1000, 1000);*/

		configuration.setSeries(series);

		RangeSelector rangeSelector = new RangeSelector();
		rangeSelector.setSelected(1);
		configuration.setRangeSelector(rangeSelector);

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

	}
}