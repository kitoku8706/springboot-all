package com.example.spring03_shop.members.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.spring03_shop.config.auth.PrincipalDetails;
import com.example.spring03_shop.members.dto.AuthInfo;
import com.example.spring03_shop.members.dto.MembersDTO;
import com.example.spring03_shop.members.service.AuthService;
import com.example.spring03_shop.members.service.MembersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins ={"http://localhost:3000"})
//@CrossOrigin("*")

@RestController
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@Autowired
	private AuthService authService;

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	public MembersController() {

	}

	// 회원가입
	@PostMapping(value = "/member/signup")
	public ResponseEntity<AuthInfo> addMember(@RequestBody MembersDTO membersDTO) {
		membersDTO.setMemberPass(encodePassword.encode(membersDTO.getMemberPass()));
		AuthInfo authInfo = membersService.addMemberProcess(membersDTO);
		return ResponseEntity.ok(authInfo);
	}

	// http://localhost:8090/member/editinfo/dong@google.com
	// 회원정보 가져오기
    @PreAuthorize("isAuthenticated()")
//    @PreAuthorize("hasRole('ADMIN')")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
//	@PreAuthorize("principal.username==#memberEmail")
	@GetMapping(value = "/member/editinfo/{memberEmail}")
    public ResponseEntity<MembersDTO> getMember(@PathVariable("memberEmail") String memberEmail,  @AuthenticationPrincipal PrincipalDetails principal ){

//	public ResponseEntity<MembersDTO> getMember(
//			@PathVariable("memberEmail") @org.springframework.data.repository.query.Param("memberEmail") String memberEmail,
//			@AuthenticationPrincipal PrincipalDetails principal) {

		log.info("path memberEmail => {}", memberEmail);
		log.info("principal={}", principal.getUsername());

		PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String authenticatedUsername = principalDetails.getUsername();
		log.info("authenticatedUsername=> {}", authenticatedUsername);

		MembersDTO memDTO = membersService.getByMemberProcess(memberEmail);
		return ResponseEntity.ok(memDTO);
	}
    
    // 회원정보 수정
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping(value="/member/update")
    public ResponseEntity<AuthInfo> updateMember(@RequestBody MembersDTO membersDTO){
    	membersDTO.setMemberPass(encodePassword.encode(membersDTO.getMemberPass()));
    	return ResponseEntity.ok(membersService.updateMemberProcess(membersDTO));
    	
    }
    
    // 회원 탈퇴
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value="/member/delete/{memberEmail}")
    public ResponseEntity<Void> deleteMember(@PathVariable("memberEmail") String memberEmail){
    	membersService.deleteMemberProcess(memberEmail);
    	return ResponseEntity.ok(null);
    }
    
    // 회원 로그아웃
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value="/member/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization-refresh") String refreshToken){
    	String email = JWT.require(Algorithm.HMAC512("mySecurityCos")).build().verify(refreshToken).getClaim("memberEmail").toString();
    	authService.deleteRefreshToken(email);
    	return ResponseEntity.ok(Map.of("message","로그아웃완료"));
    }

}
