package com.example.spring04_mybatis.ex02;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
public class Test02 {
	private final BookService service;
	@GetMapping(value="/listBook")
	public void selectList() {
		log.info("{}", service.selectList());
		for (Book book : service.selectList())
			System.out.printf("%s | %s | %s | %s\n", book.getCode(), book.getTitle(), book.getAuthor(), book.getPublisher());
	}
	
	@PostMapping(value="/insertBook")
	public void insertBook() {
		service.insertBook(new Book("IT001", "클린 코드", "로버트 마틴", "인사이트"));
	}
	
	@PutMapping(value="/updateBook")
	public void updateBook() {
		HashMap<String, String> map = new HashMap<>();
		map.put("title", "모두의 딥러닝");
		map.put("author", "송호연");
		map.put("publisher", "길벗");
		map.put("code", "IT001");
		service.updateBook(map);
	}

	@DeleteMapping(value="/deleteBook")
	public void deleteBook() {		
		service.deleteBook("IT001");
	}
	@PostMapping(value="/insertMulti")
	public void insertMulti() {
		List<Book> insertList = new ArrayList<>();
		insertList.add(new Book( "IT002", "모두의 딥러닝", "송호연", "길벗"));
		insertList.add(new Book( "IT003", "Do it! 점프 투 파이썬", "박응용", "이지스퍼블리싱"));
		service.insertMulti(insertList);		
	}
	@DeleteMapping(value="/delMulti")
	public void delMulti() {		
		service.delMulti(new String[] {"B001","B002", "B003"});
	}
	
	@GetMapping(value="/search")
	public void search(){
		log.info("{}", service.search(new Book(null, "투자", "제임스", "윌북")));
		for (Book book : service.search(new Book(null, "투자", "제임스", "윌북")))
			System.out.printf("%s | %s | %s | %s\n", book.getCode(), book.getTitle(), book.getAuthor(), book.getPublisher());
		}

	@PutMapping(value="/updateMulti")
	public void updateMulti(Book book) {
		service.updateMulti(new Book("IT003", "Do it! 점프 투 플라스크", null , null));
	}
	
	@PostMapping(value="/insertDymic")
	public void insertDymic() {
		service.insertDymic(new Book("IT004", "리팩터링", null, "한빛미디어"));
	}


}




