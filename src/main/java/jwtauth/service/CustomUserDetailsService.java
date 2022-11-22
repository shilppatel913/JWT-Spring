package jwtauth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jwtauth.repo.UserRepo;


@Service
public class CustomUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		jwtauth.models.User user=this.userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}else {
			return new CustomUserDetaills(user);
		}
		
		
		
		
		
//		// TODO Auto-generated method stub
//		if(username.equals("Shilp")) {
//			//arraylist for the permissions and authorities
//			return new User("Shilp","Shilp@1234",new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("User was not found");
//		}
	}
	
}
