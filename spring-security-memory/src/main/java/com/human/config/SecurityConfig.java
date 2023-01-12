package com.human.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity   // 해당 클래스를 스프링 시큐리티 설정 클래스로 지정
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	// 외부에서 인스턴스를 생성해와서 지금 이 클래스에서 사용할 수 있도록 하는거임
	// 의존주입을 자동으로 해와줌 (Autowired)
	// 스프링 컨테이너에 등록이 되어있어야만 함.
	// 컨테이너에 등록이 되려면

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 스프링 시큐리티 설정
		log.info("스프링 시큐리티 설정");
		
		// authentication (인증)
		// : 등록된 사용자인지 확인하여 입증하는 과정
		
		// autorization (인가)
		// : 사용자의 권한을 확인하여 권한에 따라 자원의 사용범위를 구분하여 허락하는 것 
		// 인증된 사용자인지 아닌지 확인을 해야 함.
		// 메인페이지는 누구나 사용할 수 있게 열어줘야 함.
		
		// 인가 처리
		http.authorizeHttpRequests()        // 인가 설정
			// antMatchers("자원 경로") - 인가에 대한 URL 경로를 지정	
			// permitAll()            - 모든 사용자에 허용
			// hasAnyRole("USER", "ADMIN") - 여러 권한에 대하여 허용
			// hasRole("ADMIN") 		   - 단일 권한에 대하여 허용
			
			.antMatchers("/").permitAll()   //    "/"	경로는 모든 사용자에 허용한다. 
			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()   // 모든 요청에 대하여 인증된 사용자만 허용하도록 설정
			                                // 로그인이 된 사용자만 볼 수 있도록 하는 기능임.
			;
		
		
		
		// ↓↓↓↓↓↓↓↓↓↓↓↓↓ 로그인 설정임
		
		http.formLogin()    // 로그인 폼 url 경로는 모든 사용자에 허용
			.permitAll()
			;
	
		// 로그아웃 설정
		http.logout()		// 로그아웃 기본 경로 : /logout
		    .permitAll()  	// 로그아웃 url 경로는 모든 사용자에 허용
		    ;
	
			// 인증된 사용자가 들어갈 수 있는 페이지 & 관리자 권한으로 들어갈 수 있는 페이지 구축할거임
	
	}

	// 사용자 등록 메소드를 정의하겠다 ↓↓↓↓↓↓↓↓↓↓↓
	// 인증 관리 설정 메소드
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// AuthenticationManagerBuilder : 인증 관리 객체
		auth.inMemoryAuthentication()							// 인증 방식 : 인메모리 방식으로 지정 가능
			// 사용자 등록 - 아이디 / 패스워드 / 권한
			// passwordEncoder.encode("비밀번호")  :  비밀번호를 암호화하는 메소드 
			.withUser("user").password(passwordEncoder.encode("123456")).roles("USER") 
		 	.and()  // --> 여러 사용자를 등록한다는 의미임.
		 	// 관리자 등록
		 	.withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN")
		 	; 
	}  // --> 이렇게 하면 인메모리 방식으로 사용자가 등록됨
	
	
	
	
	
}

