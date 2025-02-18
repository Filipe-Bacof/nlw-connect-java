package br.com.bacof.nlw_connect.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bacof.nlw_connect.model.Event;

public interface EventRepo extends CrudRepository<Event, Integer> {
	public Event findByPrettyName(String prettyName);
}
