package com.example.spring03_shop.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03_shop.board.dto.BoardDTO;
import com.example.spring03_shop.board.dto.PageDTO;
import com.example.spring03_shop.board.entity.BoardEntity;
import com.example.spring03_shop.board.repository.BoardRepository;

@Service
public class BoardServiceImp implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public BoardServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public long countProcess() {
		long cnt = boardRepository.count();
		return cnt;
	}

	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		List<BoardEntity> listBoardEntity = boardRepository.findAll();
		List<BoardDTO> listBoradDTO = listBoardEntity.stream().map(BoardDTO::toDTO).collect(Collectors.toList());
		return listBoradDTO;
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardDTO contentProcess(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProcess(BoardDTO dto, String urlpath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProcess(int num, String urlpath) {
		// TODO Auto-generated method stub

	}

}
