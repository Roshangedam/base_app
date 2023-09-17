package com.base.app.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.base.app.Exception.UserAuthenticationException;
import com.base.app.models.user.User;
import com.base.app.services.user.UserService;


@Service
public class AuthService {
  
	
	@Autowired
	private UserService userService;
	
    public User registerNewUser(User user, BindingResult bindingResult) {
    	
        if (this.userService.isUserExist(user.getEmail())) {
            throw new UserAuthenticationException(HttpStatus.BAD_REQUEST,"Email "+user.getEmail()+" is Already registered");
        }               
        return this.userService.getUserRepository().save(user);
    }
    
    public User loginUser(User user) {                    
        return this.userService.getUserRepository().save(user);
    }
	
}
