package com.cibertec.exam.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

		http
        .authorizeHttpRequests()
        	// Para permitir nuestro js y css (en nuestra carpeta static)
			.requestMatchers("/*.js", "/*.css").permitAll()
            .requestMatchers("/resources/**").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
        	// login permit
            .loginPage("/login")
            // success send
            .permitAll().defaultSuccessUrl("/home")
            .and()
        .logout()
            .logoutUrl("/logout")
            .permitAll();
		
		return http.build();
	}
	
	/*@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager admin = new InMemoryUserDetailsManager();
		admin.createUser(User.withUsername("admin")
					.password(passwordEncoder().encode("admin123"))
					.roles("ADMIN")
					.build());
		InMemoryUserDetailsManager user = new InMemoryUserDetailsManager();
		user.createUser(User.withUsername("user")
					.password(passwordEncoder().encode("user123"))
					.roles("USER")
					.build());
		
		return admin;
	}*/
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin123"))
				.roles("ADMIN")
				.build();
		UserDetails user2 = User.builder()
				.username("user")
				.password(passwordEncoder().encode("user123"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService())
									.passwordEncoder(passwordEncoder())
									.and()
									.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
