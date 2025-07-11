package com.example.spring04_mybatis.ex02;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//BookService => BookMapper => bookMapper.xml
@Mapper
public interface BookMapper {

	public List<Book> selectList();
	public void insertBook(Book book);
	public void updateBook(HashMap<String, String> map);
	public void deleteBook(String code);
	public void insertMulti(List<Book> list);
	public void delMulti(String[] codeArr);
	public List<Book> search(Book book);
	public void updateMulti(Book book);
	public void insertDymic(Book book);





}
