package com.vaadin.devon;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.devon.serviceImpl.TemperatureRecordsImpl;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
public class VaadinAwesomeApplication {

	private static Logger log = LoggerFactory.getLogger(VaadinAwesomeApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(VaadinAwesomeApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
		// our rest resources will be available in the path /rest/*
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registration;
	}



}
