package com.base.app.controller.authentication;


import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.app.Exception.UserAuthenticationException;
import com.base.app.models.user.User;
import com.base.app.services.authentication.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> craeateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
	    System.err.println("post user");
	    return handleResponse(() -> this.authService.registerNewUser(user,bindingResult));
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
	    return handleResponse(() -> this.authService.loginUser(user));
	}
	
	private ResponseEntity<?> handleResponse(Supplier<?> action) {
	    try {
	        Object result = action.get();
	        return ResponseEntity.ok(result);
	    }catch (UserAuthenticationException e) {
	        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
	    }catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
}
