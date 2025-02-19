package br.com.bacof.nlw_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bacof.nlw_connect.dto.ErrorMessage;
import br.com.bacof.nlw_connect.dto.SubscriptionResponse;
import br.com.bacof.nlw_connect.exception.EventNotFoundException;
import br.com.bacof.nlw_connect.exception.SubscriptionConflictException;
import br.com.bacof.nlw_connect.exception.UserIndicatorNotFoundException;
import br.com.bacof.nlw_connect.model.User;
import br.com.bacof.nlw_connect.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;
	
	@PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userId}"})
	public ResponseEntity<?> createSubscription (
		@PathVariable String prettyName,
		@RequestBody User subscriber,
		@PathVariable(required = false) Integer userId
	) {
		try {
			SubscriptionResponse result = service.createNewSubscription(prettyName, subscriber, userId);
			if (result != null) {
				return ResponseEntity.ok(result);
			}
		} catch (EventNotFoundException exception) {
			return ResponseEntity.status(404).body(new ErrorMessage(exception.getMessage()));
		} catch (SubscriptionConflictException exception) {
			return ResponseEntity.status(409).body(new ErrorMessage(exception.getMessage()));
		} catch (UserIndicatorNotFoundException exception) {
			return ResponseEntity.status(404).body(new ErrorMessage(exception.getMessage()));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/subscription/{prettyName}/ranking")
	public ResponseEntity<?> generateRankingByEvent(@PathVariable String prettyName) {
		try {
			return ResponseEntity.ok(service.getCompleteRanking(prettyName));
		} catch (EventNotFoundException exception) {
			return ResponseEntity.status(404).body(new ErrorMessage(exception.getMessage()));
		}
	}
}
