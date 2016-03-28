package com.vaadin.devon.restapi;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.vaadin.devon.boardcast.Broadcaster;
import com.vaadin.devon.entity.Greeting;
import com.vaadin.devon.event.LoginEvent;
import com.vaadin.devon.event.TempEvent;
import com.vaadin.devon.firstscreen.Default;
import com.vaadin.ui.UI;



@Path("/restapi")
@Component
public class TestRest {

	private static final String template = "Hello, %s!";
	private AtomicLong counter = new AtomicLong();
	Broadcaster bc = new Broadcaster();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/greeting")
	public Greeting greeting(@QueryParam("name") String name) {
		// System.out.println(request.getHeader("Server"));
	//	bc.broadcast(name);
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	
	@GET
	@Path("/sendtemp")
	public String temp(@QueryParam("temp") String temp, @QueryParam("y")String y){
		
		String str = temp+","+y;
		bc.broadcast(str);
	/*	EventBus eventBus = new EventBus();
		eventBus.register(new Default());
		eventBus.post(new TempEvent(str));*/
		return "got it";
	}
}
