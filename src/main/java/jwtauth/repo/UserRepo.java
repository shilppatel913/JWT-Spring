package jwtauth.repo;

import org.springframework.data.repository.CrudRepository;

import jwtauth.models.User;

public interface UserRepo extends CrudRepository<User, Long>{
		
	//this method will automatically return the user with the given username
	public User findByUsername(String username);
}
