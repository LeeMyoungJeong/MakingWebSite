package com.human.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration		// 해당 클래스를 빈 등록 클래스로 지정함.
public class EncodeUtil {

	@Bean			// 해당 객체를 컨테이너에 빈으로 등록
	                // 의존성 주입 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
