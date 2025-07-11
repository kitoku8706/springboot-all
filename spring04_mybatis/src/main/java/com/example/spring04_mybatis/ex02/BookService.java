package com.example.spring04_mybatis.ex02;


import java.util.HashMap;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;


@Transactional
@Service
@RequiredArgsConstructor
public class BookService {
	private final BookMapper bookMapper;


	public List<Book> selectList(){
		return bookMapper.selectList();
	}
	
	public void insertBook(Book book) {
		bookMapper.insertBook(book);
	}
	
	public void updateBook(HashMap<String, String> map) {
		bookMapper.updateBook(map);
	}
	
	public void deleteBook(String code) {
		bookMapper.deleteBook(code);
	}
	
	public void insertMulti(List<Book> list) {
		bookMapper.insertMulti(list);
	}
	
	public void delMulti(String[] codeArr) {
		bookMapper.delMulti(codeArr);
	}
	
	public List<Book> search(Book book){
		return bookMapper.search(book);
	}
	
	public void updateMulti(Book book) {
		bookMapper.updateMulti(book);
	}
	
	public void insertDymic(Book book) {
		bookMapper.insertDymic(book);
	}
}




