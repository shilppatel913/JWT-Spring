package jwtauth;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jwtauth.models.User;
import jwtauth.repo.UserRepo;

@SpringBootApplication
public class JwtauthApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepo userRepo;
	Random random=new Random();
	public void createUser() {
		User user=new User();
		user.setId(new Long(random.nextInt(100)));
		user.setUsername("Isha");
		user.setPassword("Isha@1234");
		user.setEmail("isha@gmail.com");
		user.setRole("Admin");
		user.setEnabled(true);
		User user1 =this.userRepo.save(user);
		System.out.println(user1);
		return;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JwtauthApplication.class, args);
		System.out.println("Application has started running");
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		//when the application starts run method will implement the creatUser method
		createUser();
		
	}

}
