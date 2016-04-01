package com.vaadin.devon.restapi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.devon.boardcast.Broadcaster;
import com.vaadin.devon.db2repo.TempRepo;
import com.vaadin.devon.entity.Greeting;
import com.vaadin.devon.entity.TemperatureRecords;

@Path("/restapi")
@Component
public class TestRest {

	
	@Autowired
	TempRepo tRepo;

	@Autowired
	private TemperatureRecords tr;

	private static final String template = "Hello, %s!";
	private AtomicLong counter = new AtomicLong();
	Broadcaster bc = new Broadcaster();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/greeting")
	public Greeting greeting(@QueryParam("name") String name) {
		// System.out.println(request.getHeader("Server"));
		// bc.broadcast(name);
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GET
	@Path("/sendtemp")
	public String temp(@QueryParam("temp") String temp, @QueryParam("email") String y,@QueryParam("timestamp") String z) {

		String str = temp + "," + y+","+z;
		bc.broadcast(str);
		/*
		 * EventBus eventBus = new EventBus(); eventBus.register(new Default());
		 * eventBus.post(new TempEvent(str));
		 */
		return "got it";
	}
	
	@GET
	@Path("/showall")
	@Produces("application/json")
	public Iterable<TemperatureRecords> showAll() {

		
		return tRepo.findAll();
	}


	@GET
	@Path("/testinsert")
	@Produces("application/json")
	public TemperatureRecords insertRecord() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date today = Calendar.getInstance().getTime();

		String reportDate = df.format(today);
		System.out.println(reportDate);
		java.sql.Timestamp ts = java.sql.Timestamp.valueOf(reportDate);
		// tr.setId(1L);
		tr.setEmail("loudywen1@gmail.com");
		tr.setDevice("st100");
		tr.setTemperature(600.0);
		tr.setTimeStamp(ts);
		tRepo.save(tr);

		return tr;
	}
}
