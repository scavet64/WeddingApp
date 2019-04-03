package com.scavettapps.wedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scavettapps.wedding.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
