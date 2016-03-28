package com.vaadin.devon.dummyImpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.devon.boardcast.Broadcaster;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;

public class DummyTimelineSingleLine extends AbstractVaadinChartExample implements View,Broadcaster.BroadcastListener{

	private double temp = 0;
	private double tempholder = 0;
	private int count = 0;
	
	public DummyTimelineSingleLine() {
		Broadcaster.register(this);
	}
	
	
    @Override
    public String getDescription() {
        return "Single line chart with timeline";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("Time line whatever");

        DataSeries series = new DataSeries();
   /*     for(StockPrices.PriceData data : StockPrices.fetchAaplPrice()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            dataSeries.add(item);
        }*/
        
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