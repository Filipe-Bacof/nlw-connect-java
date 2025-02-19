package br.com.bacof.nlw_connect.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bacof.nlw_connect.model.Event;
import br.com.bacof.nlw_connect.model.Subscription;
import br.com.bacof.nlw_connect.model.User;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
	public Subscription findByEventAndSubscriber(Event event, User user);
}
