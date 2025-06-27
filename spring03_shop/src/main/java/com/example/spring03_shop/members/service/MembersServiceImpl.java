package com.example.spring03_shop.members.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring03_shop.members.dto.AuthInfo;
import com.example.spring03_shop.members.dto.ChangePwdCommand;
import com.example.spring03_shop.members.dto.MembersDTO;
import com.example.spring03_shop.members.entity.MembersEntity;
import com.example.spring03_shop.members.repository.MembersRepository;
@Service
public class MembersServiceImpl implements MembersService {
	@Autowired
	private MembersRepository membersRepository;
	public MembersServiceImpl() {
	}
	@Override
	public AuthInfo addMemberProcess(MembersDTO dto) {
		membersRepository.save(dto.toEntity());
		return new AuthInfo(dto.getMemberEmail(), dto.getMemberPass(), dto.getMemberName(), dto.getAuthRole());
	}
	@Override
	public AuthInfo loginProcess(MembersDTO dto) {
		// TODO Auto-generated method stub
		return null;
	} 
	public MembersDTO getByMemberProcess(String memberEmail) {
		Optional<MembersEntity> optMembersEntity = membersRepository.findById(memberEmail);
		return MembersDTO.toDTO(optMembersEntity.get());
	}
	@Override
	public AuthInfo updateMemberProcess(MembersDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updatePassProcess(String memberEmail, ChangePwdCommand changePwd) {
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteMemberProcess(String memberEmail) {
		// TODO Auto-generated method stub
	}
	
	
}


