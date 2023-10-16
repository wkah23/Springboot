package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	
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
			.loginPage("/loginForm")	// 로그인 페이지 요청명
			// 로그인처리(폼 전송)를 위한 요청명
			.loginProcessingUrl("/j_spring_security_check")
			// 실패시 이동할 경로
//			.failureUrl("/loginForm?error")
			// 로그인 성공시 이동할 경로
//			.defaultSuccessUrl("/")
			.failureHandler(authenticationFailureHandler)
			// 아이디 입력상자의 name 속성
			.usernameParameter("j_username")
			// 아이디 입력상자의 password 속성
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
//	@Bean
//	protected UserDetailsService users() {
//		UserDetails user = User.builder()
//				.username("user")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER")
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER","ADMIN")
//				.build();
//		// 메모리에 사용자 정보를 담는다.
//		return new InMemoryUserDetailsManager(user,admin);
//	}
//	/*
//	 	시큐리티 5 에서는 패스워드를 반드시 암호화해서 저장해야 한다.
//	 	encode() 를 호출할 때마다 패스워드가 변경된다.
//	 */
//	// passwordEncoder()
//	// 버전업 되면서 아래와 같이 수정됨.
//	// 내부적으로 접두어를 붙힐 수 있도록 직접 패스워드 인코더를 지정하지 않는다.
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT name AS userName, password, ENABLED"
							+ " FROM user_list WHERE name = ?")
		.authoritiesByUsernameQuery("SELECT name AS userNamem, AUTHORITY "
							+ " FROM user_list WHERE name = ?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
