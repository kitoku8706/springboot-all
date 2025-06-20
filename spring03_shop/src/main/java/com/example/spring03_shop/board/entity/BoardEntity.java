package com.example.spring03_shop.board.entity;

import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "board")
public class BoardEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
//	@SequenceGenerator(name = "board_seq_generator", sequenceName = "board_num_seq", allocationSize = 1)
	private Long num;
	private Integer readCount, ref, reStep, reLevel;
	private String  subject, content, ip, memberEmail;
	private Date regDate;
	private String upload;
	
	
	
	
	
	
}



/*

=====================================
       그룹      출력순서    출력들여쓰기
num    ref    re_step   re_level
1      1         0       0               => 제목글1
2      2         0       0               => 제목글2
3      1         4       1               => 제목1  => 답변
4      1         1       1               => 제목1  => 답변
5      1         2       2               => 제목1  => 답변4  => 답변
6      6         0       0               => 제목글3
7      1         3       3                 => 제목1  => 답변4  => 답변5 => 답변
ref DESC,  re_step ASC
     내림차순        올림차순
     
제목6
제목2
제목1
	답변4
 		답변5
    		답변7
	답변3


*/