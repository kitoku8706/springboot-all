package com.example.spring03_shop.members.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
//로그인 성공 후 인증 상태 정보를 세션에 보관할 때 사용
public class AuthInfo {
	private String memberEmail; // 이메일
	private String memberPass; // 비밀번호
	private String memberName; // 이름
	private Role authRole; // 회원구분

	public AuthInfo() {
	}

	public AuthInfo(String memberEmail, String memberPass) {
		super();
		this.memberEmail = memberEmail;

		this.memberPass = memberPass;
	}

	public AuthInfo(String memberEmail, String memberName, String memberPass) {
		super();
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberPass = memberPass;
	}

	public AuthInfo(String memberEmail, String memberName, String memberPass, Role authRole) {
		super();
		this.memberEmail = memberEmail;
		this.memberName = memberName;
		this.memberPass = memberPass;
		this.authRole = authRole;
	}

}
