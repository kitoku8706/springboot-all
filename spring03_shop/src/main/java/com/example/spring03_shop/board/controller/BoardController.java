package com.example.spring03_shop.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring03_shop.board.dto.PageDTO;
import com.example.spring03_shop.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
public class BoardController {

    private final PageDTO pageDTO;
	@Autowired
	private BoardService boardService;
	
	private int currentPage;
	private  PageDTO pdto;
	public BoardController(PageDTO pageDTO){
        this.pageDTO = pageDTO;

    }

	// http://localhost:8090/board/list/1
	@GetMapping(value = "/board/list/{currentPage}")
	public ResponseEntity<Map<String, Object>> listExecute(@PathVariable("currentPage") int currentPage){
		Map<String, Object> map = new HashMap<>();
		
		long totalRecord = boardService.countProcess();
		log.info("totalRecord: {}",totalRecord);
		
		if(totalRecord >=1) {
			this.currentPage = currentPage;
			this.pdto =new PageDTO(this.currentPage, totalRecord);
			
			map.put("boardList", boardService.listProcess(pdto));
			map.put("pv", this.pdto);
		}
		return ResponseEntity.ok().body(map);
	}
	
}
