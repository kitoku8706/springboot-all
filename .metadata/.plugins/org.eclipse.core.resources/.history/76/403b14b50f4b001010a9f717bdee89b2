package com.example.spring01_mvc.part04.controller;

import java.util.HashMap;

import javax.print.DocFlavor.STRING;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


//예시 URL:
//http://localhost:8090/paramcall?code=a001&price=1000
//http://localhost:8090/paramcall?code=a001
//http://localhost:8090/paramcall?price=1000
//http://localhost:8090/paramcall

@Slf4j
@RestController // REST 컨트롤러, 반환값을 JSON으로 변환해 클라이언트에 전달
public class ParamController {
	
	// GET 방식 요청 중 /paramcall 주소와 매핑
    // 요청 URL의 쿼리 파라미터를 @RequestParam으로 받아 처리
	@GetMapping(value="/paramcall")
	public HashMap<String,Object> paramCall(@RequestParam (name="code",required = true, defaultValue="p000") String code,
											@RequestParam (name="price",required = false, defaultValue="0") int price) {
		
		// required = false일때 integer price선언하면, 오류없이 null을 받아서 정상처리한다. 
		// required = false일때 int price선언하면, 오류가 발생한다.
		
		log.info("code:{},price:{}", code,price); 
		// 로그에 code와 price 값 출력
        // "{}"는 자리 표시자, 뒤의 변수 값이 순서대로 들어감
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);// code 값 저장
		map.put("price", price); // price 값 저장
		return map; // HashMap이 JSON 형태로 변환되어 응답됨
																	
	}
}