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
				/*
        			스프링 부트 3.0부터 적용된 스프링 시큐리티 6.0 에서 forward 방식 페이지 
        			이동에도 default로 인증이 걸리도록 변경되어서 JSP나 타임리프 등 컨트롤러에서 
        			화면 파일명을 리턴해 ViewResolver가 작동해 페이지 이동을 하는 경우 이처럼 
        			설정을 해야 한다.
        		*/
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				/*
				 	/ : 모든 요청명에 대해 권한없이 접근할 수 있다.
				 */
				.requestMatchers("/").permitAll()
				// 정적 리소스(static 하위)에 권한없이 접근할 수 있다.
				.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
				// 권한없이 접근할 수 있다.
				.requestMatchers("/guest/**").permitAll()
				// USER, ADMIN 권한 중 하나가 있어야 접근할 수 있다.
				.requestMatchers("/member/**").hasAnyRole("USER","ADMIN")
				// ADMIN 권한만 접근할 수 있다.
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
			);
		// 로그인 페이지 설정(시큐리티의 디폴트 페이지를 사용한다.)
		http.formLogin((formLogin) ->
						formLogin.permitAll()); // 기본 로그인 페이지
		// 로그 아웃 기본 설정 (/logout으로 인증 해제)
		http.logout((logout) ->
					logout.permitAll());
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
