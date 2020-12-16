package com.formacionbdi.springboot.app.oauth.security.event;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.formacionbdi.springboot.app.commons.users.models.entity.User;
import com.formacionbdi.springboot.app.oauth.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUserService userService;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if(authentication.getAuthorities().size()==0) {
			return; // when authentication.getAuthorities().size() == 0 then user is a client application
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String message = "Success login "+ userDetails.getUsername();
		log.info(message);
		System.out.println(message);
		
		User user = userService.findByUsername(userDetails.getUsername());
		
		if (user.getAttempts() != null && user.getAttempts() > 0) {
			user.setAttempts(0);
			userService.update(user, user.getId());
		}		
		
		
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error on login: " + exception.getMessage();
		log.error(message);
		System.out.println(message);
		
		try {
			User user = userService.findByUsername(authentication.getName());
			if (user.getAttempts() == null) {
				user.setAttempts(0);
			}
			
			log.info("Initial number of attempts is: "+ user.getAttempts());
			user.setAttempts(user.getAttempts() +1);
			log.info("Updated number of attempts is: "+ user.getAttempts());
			
			if (user.getAttempts() >= 3) {
				log.error(String.format("The user %s has been disabled because of too many attempts", user.getUsername()));
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
		} catch (FeignException e) {
			log.error(String.format("The user %s does not exist on system", authentication.getName()));
		}
		
	}

}
