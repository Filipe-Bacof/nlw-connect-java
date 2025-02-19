package br.com.bacof.nlw_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bacof.nlw_connect.dto.ErrorMessage;
import br.com.bacof.nlw_connect.exception.EventNotFoundException;
import br.com.bacof.nlw_connect.exception.SubscriptionConflictException;
import br.com.bacof.nlw_connect.model.Subscription;
import br.com.bacof.nlw_connect.model.User;
import br.com.bacof.nlw_connect.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;
	
	@PostMapping("/subscription/{prettyName}")
	public ResponseEntity<?> createSubscription (@PathVariable String prettyName, @RequestBody User subscriber) {
		try {
			Subscription result = service.createNewSubscription(prettyName, subscriber);
			if (result != null) {
				return ResponseEntity.ok(result);
			}
		} catch (EventNotFoundException exception) {
			return ResponseEntity.status(404).body(new ErrorMessage(exception.getMessage()));
		} catch (SubscriptionConflictException exception) {
			return ResponseEntity.status(409).body(new ErrorMessage(exception.getMessage()));
		}
		return ResponseEntity.badRequest().build();
	}
}
