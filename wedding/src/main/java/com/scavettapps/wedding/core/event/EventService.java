package com.scavettapps.wedding.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scavettapps.wedding.domain.Event;
import com.scavettapps.wedding.domain.exception.DomainObjectNotFound;
import com.scavettapps.wedding.repository.EventRepository;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepo;
	
	public Event getEventById(Long id) {
		return eventRepo.findById(id).orElseThrow(() -> new DomainObjectNotFound("Could not find event with ID: " + id));
	}
}
