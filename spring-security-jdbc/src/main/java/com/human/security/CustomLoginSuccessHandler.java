package com.human.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

// 로그인 성공처리 클래스                                  // 스프링시큐리티에서 로그인 되고 난 다음에 어떻게할것인지 결정하는 클래스
@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request
									  , HttpServletResponse response
									  , Authentication authentication) throws ServletException, IOException {
        // 인증 처리가 되면 메소드 실행됨..
		log.info("인증 처리 성공...");
		
		// 아이디 저장 기능
		String rememberId = request.getParameter("remember-id"); // 아이디 저장 여부
		String username = request.getParameter("username");		 // 아이디
		log.info("rememberId : " + rememberId);
		
		// 아이디 저장 체크 → 쿠키 생성
		if( rememberId != null && rememberId.equals("on")) {
			Cookie cookie = new Cookie("remember-id", username );
			cookie.setMaxAge(60*60*24*10);    // 유효기간 : 10일
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		// 아이디 저장 x -> 쿠키 삭제
		else {
			Cookie cookie = new Cookie("remember-id", null );
			cookie.setMaxAge(0);    // 유효기간 : 10일
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	
		// 인증된 사용자 정보 (아이디 / 패스워드 / 권한)
		User user = (User) authentication.getPrincipal();
	
		log.info("아이디 : " + user.getUsername());
		log.info("패스워드 : " + user.getPassword());  // 보안 상 노출 x
		log.info("권한 : " + user.getAuthorities());
		
		super.onAuthenticationSuccess(request, response, authentication);
	
	
	
	}
	
	
		
}
