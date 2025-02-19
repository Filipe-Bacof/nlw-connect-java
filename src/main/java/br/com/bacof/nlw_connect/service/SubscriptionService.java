package br.com.bacof.nlw_connect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bacof.nlw_connect.model.Event;
import br.com.bacof.nlw_connect.model.Subscription;
import br.com.bacof.nlw_connect.model.User;
import br.com.bacof.nlw_connect.repository.EventRepo;
import br.com.bacof.nlw_connect.repository.SubscriptionRepo;
import br.com.bacof.nlw_connect.repository.UserRepo;

@Service
public class SubscriptionService {
	
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	public Subscription createNewSubscription(String eventName, User user) {
		Event event = eventRepo.findByPrettyName(eventName);
		user = userRepo.save(user);
		
		Subscription subscription = new Subscription();
		subscription.setEvent(event);
		subscription.setSubscriber(user);
		
		Subscription result = subscriptionRepo.save(subscription);
		return result;
	}
}
