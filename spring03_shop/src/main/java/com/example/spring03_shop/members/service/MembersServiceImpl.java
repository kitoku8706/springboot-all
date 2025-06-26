package com.example.spring03_shop.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring03_shop.members.dto.AuthInfo;
import com.example.spring03_shop.members.dto.ChangePwdcommand;
import com.example.spring03_shop.members.dto.MembersDTO;
import com.example.spring03_shop.members.repository.MembersRepository;

@Service
public class MembersServiceImpl implements MembersService{
	@Autowired
	private MembersRepository membersRepository;
	
	public MembersServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AuthInfo addMemberProcess(MembersDTO dto) {
		membersRepository.save(dto.toEntity());
		return new AuthInfo(dto.getMemberEmail(),dto.getMemberPass(),dto.getMemberName(),dto.getAuthRole());
	}

	@Override
	public AuthInfo loginProcess(MembersDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MembersDTO getByMemberProcess(String memberEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthInfo updateMemberProcess(MembersDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePassProcess(String memberEmail, ChangePwdcommand changePwd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMemberProcess(String memberEmail) {
		// TODO Auto-generated method stub
		
	}
	

}
