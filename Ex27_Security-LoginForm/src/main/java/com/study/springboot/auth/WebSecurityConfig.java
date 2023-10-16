package com.study.springboot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	// 시큐리티 사용을 위한 빈을 생성한다.
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable())
			.authorizeHttpRequests(request -> request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/guest/**").permitAll()
				.requestMatchers("/member/**").hasAnyRole("USER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
			);
		
		http.formLogin(login -> login
			.loginPage("/loginForm")
			.loginProcessingUrl("/j_spring_security_check")
			.failureUrl("/loginError")
			.usernameParameter("j_username")
			.passwordParameter("j_password")
			.permitAll()
		);
						
		http.logout(logout -> logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.permitAll()
		);
		
		return http.build();
	}
	// users() 메서드는 빠른 테스트를 위해 등록이 간단한 inMemory 방식의 인증 사용자를 등록
	@Bean
	protected UserDetailsService users() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("1234"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234"))
				.roles("USER","ADMIN")
				.build();
		// 메모리에 사용자 정보를 담는다.
		return new InMemoryUserDetailsManager(user,admin);
	}
	/*
	 	시큐리티 5 에서는 패스워드를 반드시 암호화해서 저장해야 한다.
	 	encode() 를 호출할 때마다 패스워드가 변경된다.
	 */
	// passwordEncoder()
	// 버전업 되면서 아래와 같이 수정됨.
	// 내부적으로 접두어를 붙힐 수 있도록 직접 패스워드 인코더를 지정하지 않는다.
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
