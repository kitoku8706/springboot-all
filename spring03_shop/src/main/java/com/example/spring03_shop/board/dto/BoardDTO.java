package  com.example.spring03_shop.board.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring03_shop.board.entity.BoardEntity;
import com.example.spring03_shop.board.repository.BoardRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import members.dto.MembersDTO;


 @Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BoardDTO {

	 @Autowired
    private   BoardRepository boardRepository;
	private Long num;
	private Integer readcount, ref, re_step, re_level;
	private String  subject, content, ip, memberEmail;
	private Date reg_date;
	
	// 클라이언트 MultipartFile 보냄 ->  DB에 저장 String
	//board테이블의 파일 첨부를 처리해주는 멤버변수
	private String upload;

	//form페이지에서 파일첨부를 받아 처리해주는 멤버변수
	private MultipartFile filename;


    BoardDTO(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
	
	
	// DTO -> Entity
	public BoardEntity toEntity() {
		return BoardEntity.builder()
			.num(num)
			.readcount(readcount)
			.ref(ref)
			.re_step(re_step)
			.re_level(re_level)
			.subject(subject)
			.content(content)
			.ip(ip)				
			.reg_date(reg_date)
			.upload(upload)
			.member_email(member_email)
			.build();
	}
	
	
 // Entity -> DTO
 	public static BoardDTO toDTO(BoardEntity boardEntity) {
 		return BoardDTO.builder()
 				.num(boardEntity.getNum())
 				.readcount(boardEntity.getReadcount())
 				.ref(boardEntity.getRef())
 				.re_step(boardEntity.getRe_step())
 				.re_level(boardEntity.getRe_level())
 				.subject(boardEntity.getSubject())
 				.content(boardEntity.getContent())
 				.ip(boardEntity.getIp())				
 				.reg_date(boardEntity.getReg_date())
 				.upload(boardEntity.getUpload())
 				.member_email(boardEntity.getMember_email())
 				.build();
 	}



	
	

}
