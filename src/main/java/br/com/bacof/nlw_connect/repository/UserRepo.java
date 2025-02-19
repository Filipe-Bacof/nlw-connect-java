package br.com.bacof.nlw_connect.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bacof.nlw_connect.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
