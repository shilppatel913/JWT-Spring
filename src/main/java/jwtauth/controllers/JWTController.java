package jwtauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jwtauth.helper.JwtUtil;
import jwtauth.models.JwtRequest;
import jwtauth.models.JwtResponse;
import jwtauth.service.CustomUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JWTController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//you will pass your userame and password in the parameters and it will generate a token for you in response
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
		
		System.out.println(jwtRequest);
		try {
			
			
			//first we will authenticate the user  and after that only we will create a token
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
			
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//after the user is authenticated token will be generated and send back as a reponse
		UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}
