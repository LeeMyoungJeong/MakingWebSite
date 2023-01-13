package com.human.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.domain.Board;
import com.human.domain.Member;

import lombok.extern.slf4j.Slf4j;
//  여기서 하고 싶은건 뭐냐면 
/*  ResponseEntity<> 응답 객체를 사용해보자는것이다
 * - 응답을 할 때 메시지의 헤더정보와 내용을 설정하는 객체
 * - 제네릭은 객체를 생성할 때 사용할 타입을 객체를 생성 시 정의
 * - ResponseEntity<응답할 데이터의 타입>(객체, 상태코드);
 * - ex) new ResponseEntity<String>("OK", HttpStatus.OK);  --> 이러면 OK라는 문자열을 200이라는 상태코드로 바꿔 전송 가능 
 * - ex) new ResponseEntity<Board>( new Board(), HttpStatus.OK); 
 *       * HttpState.상태코드상수  
 */
@Slf4j
@Controller
@RequestMapping("/response")
public class ResponseEntityController {
		
	@ResponseBody
	@GetMapping("/void")
	public ResponseEntity<Void> responseVoid() {
		log.info("상태코드만 응답 - 200 OK");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/string")
	public ResponseEntity<String> responseString() {
		log.info(" COMPLETE - 200 OK");
		return new ResponseEntity<String>("COMPLETE", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/board")
	public ResponseEntity<Board> responseBoard() {
		log.info(" Board - 200 OK");
		Board board = new Board(1, "제목", "작성자", "내용", new Date(), new Date());
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/member")
	public ResponseEntity<Member> responseMember() {
		log.info(" Member - 200 OK");
		Member member = new Member(1, "KHM", "123456", "김휴먼", "khm@human.com", new Date(), new Date());
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/list/board")
	public ResponseEntity<List<Board>> boardList() {	
	  
		List<Board> boardList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Board board = new Board(i+1, "제목", "작성자", "내용", new Date(), new Date());
			boardList.add(board);
		}
		log.info("Board 객체 응답...");
		return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/list/member")
	public ResponseEntity<List<Member>> memberList() {	
	  
		List<Member> memberList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Member member = new Member(i+1, "human","123456", "김휴먼", "khm@human.com",new Date(), new Date());
			memberList.add(member);
		}
		log.info("Member 객체 응답...");
		return new ResponseEntity<List<Member>>(memberList, HttpStatus.OK);
	}
	@ResponseBody
	@GetMapping("/map/board")
	public ResponseEntity<Map<String, Board>> boardMap( ) {
		// 맵은 키와 객체로 구성됨
		log.info("boardMap 요청");
		Map<String, Board> boardMap  = new HashMap();
		
		for (int i = 0; i < 5; i++) {
			boardMap.put("게시글1" + (i+1), new Board((i+1), "제목", "작성자", "내용", new Date(), new Date()));
		}
		return new ResponseEntity<Map<String,Board>>(boardMap,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/map/member")
	public ResponseEntity<Map<String, Member>> memberMap( ) {
		// 맵은 키와 객체로 구성됨
		log.info("memberMap 요청");
		Map<String, Member> memberMap  = new HashMap();
		
		for (int i = 0; i < 5; i++) {
			memberMap.put("회원" + (i+1), new Member((i+1), "human", "123456", "김휴먼", "khm@human.com", new Date(), new Date()));
		}
		return new ResponseEntity<Map<String,Member>> (memberMap,HttpStatus.OK);
	}
	
	// 이미지 파일 데이터를 응답
	@ResponseBody 
	@GetMapping("/img")
	public ResponseEntity<byte[]> responseImg() throws Exception {
		
		ResponseEntity<byte[]> response = null;
		
		try {
			 File file = new File("C:\\eeeeemj\\upload\\test.jpeg");
			 // java.nio.file.Files  이용해서   File to byte[] 변환 가능
			 // springframework  dldygotj  File to byte[] 변환 가능
			 // - FileCopyUtils : 파일 관련 유용한 기능을 제공하는 클래스(파일복사 등)
			 byte [] imgData = FileCopyUtils.copyToByteArray(file);
		
			 // File  객체를 byte[]배열로 변환해줘야 함
			 // byte[] imgData = Files.readAllBytes( file.toPath());
			 
			 // 헤더 정보 객체
			 HttpHeaders header = new HttpHeaders();
			 header.setContentType(MediaType.IMAGE_JPEG);
			 
			 // new ResponseEntity<객체타입>(응답할 객체, 상태코드);
			 // new ResponseEntity<객체타입>(응답할 객체, 헤더객체, 상태코드); -- 리스펀스 엔티티의 생성자가 오버로딩됨
			 response = new ResponseEntity<byte[]>(imgData, header, HttpStatus.OK); 
			 
		} catch(IOException e) {
			e.printStackTrace();
			// 400 - 클라이언트의 잘못된 요청
			response = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
		
	
	}
	
	// 이미지 파일 다운로드
	@ResponseBody 
	@GetMapping("/download/img")
	public ResponseEntity<byte[]> downloadImg() throws Exception {
		
		ResponseEntity<byte[]> response = null;
		
		try {
			 File file = new File("C:\\eeeeemj\\upload\\test.jpeg");
			 // java.nio.file.Files  이용해서   File to byte[] 변환 가능
			 // springframework  dldygotj  File to byte[] 변환 가능
			 // - FileCopyUtils : 파일 관련 유용한 기능을 제공하는 클래스(파일복사 등)
			 byte [] imgData = FileCopyUtils.copyToByteArray(file);
		
			 // File  객체를 byte[]배열로 변환해줘야 함
			 // byte[] imgData = Files.readAllBytes( file.toPath());
			 
			 // 헤더 정보 객체
			 HttpHeaders header = new HttpHeaders();
			 header.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 일반 파일
			 // Content-Disposition : "attachment; filename = test.jpeg"
			 String fileName = "test.jpeg";
			 fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			 header.add("Content-Disposition", "attachment; filename=\"" + fileName  + "\"");
			 
			 // new ResponseEntity<객체타입>(응답할 객체, 상태코드);
			 // new ResponseEntity<객체타입>(응답할 객체, 헤더객체, 상태코드); -- 리스펀스 엔티티의 생성자가 오버로딩됨
			 // HttpStatus.CREATED : 201 상태코드 : 요청 성공 및 자원 생성

			 response = new ResponseEntity<byte[]>(imgData, header, HttpStatus.OK); 
			 
		} catch(IOException e) {
			e.printStackTrace();
			// 400 - 클라이언트의 잘못된 요청
			response = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
		
		
	}
	
	
	
	// 오늘까지 한것은 클라이언트에서 서버로 어떻게 요청하는가
	// url을 어떤 조건으로 맵핑하는가
	// 맵핑된 조건에 대해 어떻게 응답을 할것인가
	// 보이드 타입으로 했을 때에는 같은 경로 html 
	
	
	
	// @ResponseBody : 응답할 데이터를 요청 메시지 본문에 매핑하는 어노테이션 
	// 
}
