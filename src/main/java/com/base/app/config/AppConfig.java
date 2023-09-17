package com.base.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

	@Bean
	public SecurityFilterChain myConfigFilter(HttpSecurity http) throws Exception {
		System.err.println("security is running");
		
		http.cors(cs-> cs.disable()).csrf(cs-> cs.disable())
			.authorizeHttpRequests(rs-> rs.anyRequest().authenticated())
			.httpBasic();		
		return http.build();
	}	
	
	@Bean
	public UserDetailsService baseUser(){		
		UserDetails tester=User.builder().username("test").password(this.ps().encode("test")).roles("TESTER").build();
		UserDetails roshan=User.builder().username("roshan").password(this.ps().encode("roshan")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(tester,roshan);
	}
	
	@Bean
	public PasswordEncoder ps(){				
		return new BCryptPasswordEncoder(10);
	}
	
	
	
}
