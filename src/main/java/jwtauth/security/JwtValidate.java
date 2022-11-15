package jwtauth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jwtauth.helper.JwtUtil;
import jwtauth.service.CustomUserDetailsService;

@Component
public class JwtValidate extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService  customUserDetailsService; 
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		
		
		//get jwt
		//validate
		String requestTokenHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		//checking null and the format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken=requestTokenHeader.substring(7);
			try {
				
				username=jwtUtil.extractUsername(jwtToken);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(username);
			//security check
			//the below three steps will check for the validity of the token
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("the token is not validated");
			}
			
			
		}
		//If the token is validated completely you will be able to access  a particular URL
		filterChain.doFilter(request, response);
		
	}

}
