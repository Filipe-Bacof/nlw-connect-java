package br.com.bacof.nlw_connect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bacof.nlw_connect.dto.SubscriptionResponse;
import br.com.bacof.nlw_connect.exception.EventNotFoundException;
import br.com.bacof.nlw_connect.exception.SubscriptionConflictException;
import br.com.bacof.nlw_connect.exception.UserIndicatorNotFoundException;
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
	
	public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
		Event event = eventRepo.findByPrettyName(eventName);
		if (event == null) {
			throw new EventNotFoundException("Evento "+eventName+" não existe");
		}
		User recoveredUser = userRepo.findByEmail(user.getEmail());
		if (recoveredUser == null) {
			recoveredUser = userRepo.save(user);
		}
		User indicator = null;
		if (userId != null) {
			indicator = userRepo.findById(userId).orElse(null);
			if (indicator == null) {
				throw new UserIndicatorNotFoundException("Usuário "+userId+" indicador não existe");
			}
		}

		Subscription subscription = new Subscription();
		subscription.setEvent(event);
		subscription.setSubscriber(recoveredUser);
		subscription.setIndication(indicator);
		
		Subscription temporarySubscription = subscriptionRepo.findByEventAndSubscriber(event, recoveredUser);
		if (temporarySubscription != null) {
			throw new SubscriptionConflictException("Já existe inscrição para o usuário "+recoveredUser.getName()+" no evento "+event.getTitle());
		}
		
		Subscription result = subscriptionRepo.save(subscription);
		return new SubscriptionResponse(result.getSubscriptionNumber(), "https://codecraft.com/subscription/"+result.getEvent().getPrettyName()+"/"+result.getSubscriber().getId());
	}
}
