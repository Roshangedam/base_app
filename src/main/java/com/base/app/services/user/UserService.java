package com.base.app.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.app.models.user.User;
import com.base.app.repository.user.UserRepository;

import lombok.Getter;

@Service
public class UserService {

	@Autowired
	@Getter
	private UserRepository userRepository;

	public boolean isUserExist(String email) {
		return this.userRepository.existsByEmail(email);
	}

	public User getUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
}
