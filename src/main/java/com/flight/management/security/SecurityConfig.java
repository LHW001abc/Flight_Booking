package com.flight.management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.flight.management.service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/").permitAll()
				.requestMatchers("/register/**").permitAll()
		        .requestMatchers("/images/**").permitAll()
		        .requestMatchers("/post_login").permitAll()
		        .requestMatchers("/admin/**").hasAuthority("admin")
		        .requestMatchers("/user/**").hasAuthority("user")
				.anyRequest().authenticated()
				)
			.formLogin(form ->
				form
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .successForwardUrl("/post_login")
                .failureForwardUrl("/post_login")
                .permitAll()
				)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/log_out")
					.invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
				)
			.exceptionHandling(configurer ->
				 configurer.accessDeniedPage("/access_denied")
				);
		return http.build();
	}

}
