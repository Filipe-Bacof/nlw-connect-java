package br.com.bacof.nlw_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bacof.nlw_connect.model.Event;
import br.com.bacof.nlw_connect.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService service;
	
	@PostMapping("/events")
	public Event addNewEvent(@RequestBody Event newEvent) {
		return service.addNewEvent(newEvent);
	}
}
