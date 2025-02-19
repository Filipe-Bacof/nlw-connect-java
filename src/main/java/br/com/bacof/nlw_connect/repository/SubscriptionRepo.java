package br.com.bacof.nlw_connect.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bacof.nlw_connect.model.Subscription;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
	
}
