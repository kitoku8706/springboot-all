package com.example.spring02_jpademo.part01.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02_jpademo.part01.dto.MemDTO;
import com.example.spring02_jpademo.part01.entity.MemEntity;
import com.example.spring02_jpademo.part01.repository.MemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemServiceImpl implements MemService{
	
	@Autowired
	private MemRepository memRepository;

	public MemServiceImpl() {
		}

	@Override
	public List<MemDTO> getByJPQL(String name) {
		List<MemEntity> listMemEntity = memRepository.findByNameJPQL(name);
		List<MemDTO> listMemDTO = listMemEntity.stream()
				.map(MemDTO::toDTO).collect(Collectors.toList());
		log.info("getByJPQL=>{}",listMemDTO);
		return listMemDTO;
	}

	@Override
	public List<MemDTO> getByCriteria(String name) {
		List<MemEntity> listMemEntity = memRepository.findByDynamicName(name);
		List<MemDTO> listMemDTO = listMemEntity.stream()
				.map(MemDTO::toDTO).collect(Collectors.toList());
		log.info("findByDynamicName=>{}",listMemDTO);
		return listMemDTO;
	}

	@Override
	public List<MemDTO> getByNativeQuery(String name) {
		List<MemEntity> listMemEntity = memRepository.findByNameNative(name);
		List<MemDTO> listMemDTO = listMemEntity.stream()
				.map(MemDTO::toDTO).collect(Collectors.toList());
		log.info("findByNameNative=>{}",listMemDTO);
		return listMemDTO;
	}

	@Override
	public List<MemDTO> getByNamedQuery(String name) {
		List<MemEntity> listMemEntity = memRepository.callNamedQuery(name);
		List<MemDTO> listMemDTO = listMemEntity.stream()
				.map(MemDTO::toDTO).collect(Collectors.toList());
		log.info("callNamedQuery=>{}",listMemDTO);
		return listMemDTO;
	}
	
	@Override
	public List<MemDTO> getMemByNum(int num) {
		List<MemEntity> listMemEntity = memRepository.findMemEntityByNumGreaterThanEqual(num);
		List<MemDTO> listMemDTO = listMemEntity.stream()
			.map(MemDTO::toDTO).collect(Collectors.toList());
	log.info("findMemEntityByNumGraeterThanEqual=>{}",listMemDTO);
	return listMemDTO;
}
	
	
	@Override
	public List<MemDTO> getMemByNameAndAge(String name, int age) {
		List<MemEntity> listMemEntity = memRepository.findMemEntityByNameAndAge(name, age);
		List<MemDTO> listMemDTO = listMemEntity.stream()
				.map(MemDTO::toDTO).collect(Collectors.toList());
		log.info("findMemEntityByNameAndAge=>{}",listMemDTO);
		return listMemDTO;
	}
	
	@Override
	public List<MemDTO> getMemByAgeIsNotNull() {
		List<MemEntity> listMemEntity = memRepository.findMemEntityByAgeIsNotNull();
		List<MemDTO> listMemDTO = listMemEntity.stream()
				.map(MemDTO::toDTO).collect(Collectors.toList());
		log.info("findMemEntityByAgeIsNull=>{}",listMemDTO);
		return listMemDTO;
	}
	
//	@Transactional
//	@Override
//	public int insertMemByNative(String name, int age, String loc) {
//		int cnt=memRepository.insertMemByNative(name, age, loc);
//		return cnt;
//	}
	
//	@Transactional
//	@Override
//	public int insertMemByNative(MemDTO memDTO) {
//		int cnt=memRepository.insertMemByNative(memDTO);
//		return cnt;
//	}
	@Transactional
	@Override
	public int insertMem(MemDTO memDTO) {
		int cnt=memRepository.insertMemByNative(memDTO);
		return cnt;
	}
	@Transactional
	@Override
	public int updateMem(MemDTO memDTO) {
		MemEntity memEntity = memDTO.toEntity();
//		int cnt = memRepository.updateMemByNative(memEntity);
		int cnt = memRepository.updateMemByJpql(memEntity);
		return cnt;
	}
	@Transactional
	@Override
	public int deleteMem(int num) {
//		int cnt= memRepository.deleteMemByNative(num);
		int cnt=memRepository.deleteMemByJpql(num);
		return cnt;
	}

	
	
	
}
