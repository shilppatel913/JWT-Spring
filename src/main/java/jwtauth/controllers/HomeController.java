package jwtauth.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		return "Home page";
	}
	
	@GetMapping("/getUser")
	public String getUser(Principal principal) {
		String username=principal.getName();
		return username;
	}
}
