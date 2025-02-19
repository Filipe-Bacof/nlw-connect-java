package br.com.bacof.nlw_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bacof.nlw_connect.model.Subscription;
import br.com.bacof.nlw_connect.model.User;
import br.com.bacof.nlw_connect.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;
	
	@PostMapping("/subscription/{prettyName}")
	public ResponseEntity<Subscription> createSubscription (@PathVariable String prettyName, @RequestBody User subscriber) {
		Subscription result = service.createNewSubscription(prettyName, subscriber);
		
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().build();
	}
}
