package com.human.domain;

import lombok.Data;

@Data
public class UserAuth {
	/*
	 * 회원권한
	 */
		private int authNo; 	//권한번호
		private String userId;  //회원 아이디
		private String auth;	// 권한
	}
	

