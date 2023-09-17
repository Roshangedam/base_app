package com.base.app.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.app.models.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);
}
