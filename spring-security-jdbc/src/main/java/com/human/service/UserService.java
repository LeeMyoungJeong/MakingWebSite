package com.human.service;

import com.human.domain.Users;

public interface UserService {

	// 회원 등록하는 메소드를 작성
	public int join(Users user) throws Exception;
	
}
