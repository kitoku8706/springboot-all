package com.example.spring03_shop.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring03_shop.board.dto.BoardDTO;
import com.example.spring03_shop.board.dto.PageDTO;
import com.example.spring03_shop.board.repository.BoardRepository;
import com.example.spring03_shop.board.service.BoardService;
import com.example.spring03_shop.common.file.FileUpload;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
public class BoardController {

	private final BoardRepository boardRepository;

	private final PageDTO pageDTO;
	@Autowired
	private BoardService boardService;

	private int currentPage;
	private PageDTO pdto;

	@Value("${spring.servlet.multipart.location}")
	private String tempDir;


	public BoardController(PageDTO pageDTO, BoardRepository boardRepository) {
		this.pageDTO = pageDTO;
		this.boardRepository = boardRepository;

	}

	// http://localhost:8090/board/list/1
	@GetMapping(value = "/board/list/{currentPage}")
	public ResponseEntity<Map<String, Object>> listExecute(@PathVariable("currentPage") int currentPage) {
		Map<String, Object> map = new HashMap<>();

		long totalRecord = boardService.countProcess();
		log.info("totalRecord: {}", totalRecord);

		if (totalRecord >= 1) {
			this.currentPage = currentPage;
			this.pdto = new PageDTO(this.currentPage, totalRecord);

			map.put("boardList", boardService.listProcess(pdto));
			map.put("pv", this.pdto);
		}
		return ResponseEntity.ok().body(map);
	}

	// 첨부파일이 있을때 @RequsetBody를 선언하면 안된다.
	// 답변글일때 ref, reStep, reLevel 담아서 넘겨야 한다.
	@PostMapping("/board/write")
	public ResponseEntity<String> writeProExecute(BoardDTO dto, HttpServletRequest req) {
		MultipartFile file = dto.getFilename();
		log.info("file: {}", file);

		// 파일 첨부가 있으면
		if (file != null && !file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, tempDir);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}
		dto.setIp(req.getRemoteAddr());
		boardService.insertProcess(dto);
		return ResponseEntity.ok(String.valueOf(1));
	}

	@GetMapping(value = "/board/view/{num}")
	public ResponseEntity<BoardDTO> viewExecute(@PathVariable("num") long num) {
		BoardDTO boardDTO = boardService.contentProcess(num);
		return ResponseEntity.ok(boardDTO);
	}

	@PutMapping(value = "/board/update")
	public ResponseEntity<Void> updateExecute(BoardDTO dto, HttpServletRequest req) {
		MultipartFile file = dto.getFilename();
		if (file != null && !file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, tempDir);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}
		boardService.updateProcess(dto, tempDir);
		return ResponseEntity.ok(null);
	}

	@DeleteMapping(value = "/board/delete/{num}")
	public ResponseEntity<Void> deleteExecute(@PathVariable("num") long num) {
		boardService.deleteProcess(num, tempDir);
		return ResponseEntity.ok(null);

	}

// http://localhost:8090/board/contentdownload/6638a2fb-626a-4dcc-b0aa-a2c32cf8be58_요약보고서(여행박사).docx	
	@GetMapping(value = "/board/contentdownload/{filename}")
	public ResponseEntity<Resource> downloadExecute(@PathVariable("filename") String filename) throws IOException {
		String fileName = filename.substring(filename.indexOf("_") + 1);

		// 파일명이 한글일때 인코딩 작업을 한다.
		String str = URLEncoder.encode(fileName, "UTF-8");
		log.info("str => {}", str);

		// 원본파일명에 공백이 있을 때, "+"표시가 되므로 공백으로 처리해줌
		str = str.replaceAll("\\+", "%20");

		Path path = Paths.get(tempDir + "\\" + filename);
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		// log.info("resource => {}", resource.contentLength());
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + str + ";");

		return ResponseEntity.ok().headers(headers).body(resource);
	}

}
