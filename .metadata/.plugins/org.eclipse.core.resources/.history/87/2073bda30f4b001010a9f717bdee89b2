package com.example.spring01_mvc.part06.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LombokController {
	


//	http://localhost:8090/mtest
	@GetMapping("/mtest")
	public MemDTO dataExecute() {
		MemDTO dto = new MemDTO();
		dto.setName("홍길동");
		dto.setAge(30);
		dto.setLoc("서울");
		log.info("dto: {}", dto);
		return dto;
	}// end main()


	// http://localhost:8090/input
	@ResponseBody
	@PostMapping("/input")
	public String inputExecute(@RequestBody MemDTO dto) {
		log.info("name:{}, age:{}, loc:{}", dto.getName(), dto.getAge(), dto.getLoc());
		return "ok";
	}


	// http://localhost:8090/add/{name}/{age}/{loc}
	@GetMapping("/add/{name}/{age}/{loc}")
	public String pathExecute(@PathVariable("name") String name, @PathVariable("age") int age,
			@PathVariable("loc") String loc) {
		log.info("name:{}, age:{}, loc:{}", name, age, loc);
		return "ok";
	}


}
