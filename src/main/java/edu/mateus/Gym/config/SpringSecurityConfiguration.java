package edu.mateus.Gym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{

		httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.anyRequest().permitAll())
				.sessionManagement(session ->  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return httpSecurity.build();
	}
}
