package com.example.spring03_shop.board.service;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03_shop.board.dto.BoardDTO;
import com.example.spring03_shop.board.dto.PageDTO;
import com.example.spring03_shop.board.entity.BoardEntity;
import com.example.spring03_shop.board.repository.BoardRepository;

@Service
public class BoardServiceImp<boardservice> implements BoardService {

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

	@Transactional
	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		List<BoardEntity> listBoardEntity = boardRepository.findPagedBoardsByRownum(pv);
		List<BoardDTO> listBoradDTO = listBoardEntity.stream().map(BoardDTO::toDTO).collect(Collectors.toList());
		return listBoradDTO;
	}

	@Transactional
	@Override
	public void insertProcess(BoardDTO dto) {
		long newID = boardRepository.getNextVal(); // 별도 시퀀스 조회 필요
		dto.setNum(newID);

		// 답변이 아니면 ref와 num을 동일하게 설정
		if (dto.getRef() == null || dto.getRef() == 0) {
			dto.setRef((int) newID);
			dto.setReStep(0);
			dto.setReLevel(0);
		} else {
			int ref = dto.getRef();
			int reStep = dto.getReStep();
			boardRepository.increaseReStep(ref, reStep);
			dto.setReStep(dto.getReStep() + 1);
			dto.setReLevel(dto.getReLevel() + 1);

		}
		dto.setRegDate(new Date(System.currentTimeMillis()));
		dto.setReadCount(0);
		BoardEntity boardEntity = dto.toEntity();
		boardRepository.save(boardEntity);
	}

	@Transactional
	@Override
	public BoardDTO contentProcess(long num) {
		boardRepository.increaseReadCount(num);
		BoardEntity boardEntity = boardRepository.findWithMemberByNum(num);
		return BoardDTO.toDTO(boardEntity);

	}

	@Transactional
	@Override
	public void updateProcess(BoardDTO dto, String tempDir) {
		String filename = dto.getUpload();
		// 수정할 첨부파일이 있으면
		if (filename != null) {
			String uploadFilename = boardRepository.getUploadFilename(dto.getNum());
			// 기존에 저장된 첨부파일이 있으면
			if (uploadFilename != null) {
				File file = new File(tempDir, uploadFilename);
				file.delete();
			}

		}

		BoardEntity boardEntity = dto.toEntity();
		boardRepository.updateBoard(boardEntity);

	}

	@Transactional
	@Override
	public void deleteProcess(long num, String tempDir) {
		String uploadFilename = boardRepository.getUploadFilename(num);
		
		//파일 첨부가 있으면
		if(uploadFilename !=null) {
			File file = new File(tempDir, uploadFilename);
			file.delete();
		}
		boardRepository.deleteById(num);
		
	}

}
