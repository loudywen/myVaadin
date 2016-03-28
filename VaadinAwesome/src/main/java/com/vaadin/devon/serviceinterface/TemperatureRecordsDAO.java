package com.vaadin.devon.serviceinterface;

import java.util.List;

import com.vaadin.devon.entity.TemperatureRecords;

public interface TemperatureRecordsDAO {
	public List<TemperatureRecords> findAll();
	
	public void createTable();
	
	public void insert(TemperatureRecords t);

}
