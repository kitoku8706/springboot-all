package com.example.spring04_mybatis.ex02;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
	String code;
	String title;
	String author;
	String publisher;

}
